package me.mattstudios.triumphchat.commands

import me.mattstudios.mf.annotations.Alias
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.annotations.Permission
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
import me.mattstudios.triumphchat.config.settings.Setting
import me.mattstudios.triumphchat.func.DEFAULT_PM_RECIPIENT
import me.mattstudios.triumphchat.func.DEFAULT_PM_SENDER
import me.mattstudios.triumphchat.func.selectMessageFormat
import me.mattstudios.triumphchat.func.sendTo
import me.mattstudios.triumphchat.locale.Messages
import me.mattstudios.triumphchat.message.ChatMessage
import me.mattstudios.triumphchat.permissions.Permission
import org.bukkit.Bukkit
import org.bukkit.entity.Player

@Command("reply")
@Alias("r")
class ReplyCommand(private val plugin: TriumphChat) : CommandBase() {

    private val userManager = plugin.userManager
    private val config = plugin.config

    @Default
    @Permission("triumphchat.reply")
    fun sendReply(
        sender: Player,
        @Completion("#empty") args: Array<String>
    ) {

        if (args.isEmpty()) {
            sendMessage("cmd.wrong.usage", sender)
            return
        }

        val author = userManager.getUser(sender)

        val replyTarget = author.replyTarget ?: run {
            plugin.locale[Messages.REPLY_NO_REPLY_TARGET].sendTo(sender)
            return
        }

        val recipient = userManager.getUser(replyTarget)

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

        author.sendMessage(senderMessage)
        recipient.sendMessage(recipientMessage)
        recipient.replyTarget = author.uuid

        if (sender.hasPermission(Permission.SOCIAL_SPY__BYPASS)) {
            return
        }

        if (Bukkit.getPlayer(replyTarget)!!.hasPermission(Permission.SOCIAL_SPY__BYPASS)) {
            return
        }

        val socialSpyMessage = ChatMessage(
            author,
            recipient,
            message,
            listOf(MessageDisplay(config[Setting.PRIVATE_MESSAGES].socialSpyFormat))
        )

        Bukkit.getOnlinePlayers()
            .filter { it.uniqueId != sender.uniqueId && it.uniqueId != recipient.uuid }
            .filter { it.hasPermission(Permission.SOCIAL_SPY) }
            .forEach { userManager.getUser(it.uniqueId).sendMessage(socialSpyMessage) }
    }

}