package me.mattstudios.triumphchat.commands

import me.mattstudios.mf.annotations.Alias
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.ChatUser
import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
import me.mattstudios.triumphchat.config.settings.Setting
import me.mattstudios.triumphchat.func.DEFAULT_PM_RECIPIENT
import me.mattstudios.triumphchat.func.DEFAULT_PM_SENDER
import me.mattstudios.triumphchat.func.selectMessageFormat
import me.mattstudios.triumphchat.func.sendTo
import me.mattstudios.triumphchat.locale.Messages
import me.mattstudios.triumphchat.message.ChatMessage
import org.bukkit.Bukkit
import org.bukkit.entity.Player

@Command("msg")
@Alias("m")
class MessageCommand(private val plugin: TriumphChat) : CommandBase() {

    private val userManager = plugin.userManager
    private val config = plugin.config
    private val locale = plugin.locale

    @Default
    fun sendMessage(
        sender: Player,
        @Completion("#players") recipient: ChatUser?,
        @Completion("#empty") args: Array<String>
    ) {

        if (recipient == null) {
            locale[Messages.MESSAGE_RECIPIENT_NO_EXIST].sendTo(sender)
            return
        }

        val author = userManager.getUser(sender)

        val message = args.joinToString(" ")

        val senderMessage = ChatMessage(
            author,
            recipient,
            message,
            author.selectMessageFormat(
                config[Setting.PRIVATE_MESSAGES].senderFormats,
                plugin.formatsConfig,
                DEFAULT_PM_SENDER
            )
        )

        val recipientMessage = ChatMessage(
            author,
            recipient,
            message,
            recipient.selectMessageFormat(
                config[Setting.PRIVATE_MESSAGES].recipientFormats,
                plugin.formatsConfig,
                DEFAULT_PM_RECIPIENT
            )
        )

        val socialSpyMessage = ChatMessage(
            author,
            recipient,
            message,
            listOf(MessageDisplay(config[Setting.PRIVATE_MESSAGES].socialSpyFormat))
        )

        author.sendMessage(senderMessage)
        recipient.sendMessage(recipientMessage)

        Bukkit.getOnlinePlayers()
                .filter { it.hasPermission("triumphchat.socialspy") }
                .forEach { userManager.getUser(it.uniqueId).sendMessage(socialSpyMessage) }

        recipient.replyTarget = author.uuid
    }

}