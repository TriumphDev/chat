package me.mattstudios.triumphchat.commands

import me.mattstudios.mf.annotations.Alias
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.ChatUser
import me.mattstudios.triumphchat.chat.ChatMessage
import me.mattstudios.triumphchat.config.settings.Settings
import me.mattstudios.triumphchat.func.DEFAULT_PM_RECIPIENT
import me.mattstudios.triumphchat.func.DEFAULT_PM_SENDER
import me.mattstudios.triumphchat.func.selectMessageFormat
import org.bukkit.entity.Player

@Command("msg")
@Alias("m")
class MessageCommand(private val plugin: TriumphChat) : CommandBase() {

    private val playerManager = plugin.userManager
    private val messageManager = plugin.messageManager
    private val config = plugin.config

    @Default
    fun sendMessage(
        sender: Player,
        @Completion("#players") recipient: ChatUser?,
        @Completion("#empty") args: Array<String>
    ) {
        if (recipient == null) {
            sender.sendMessage("Temp error")
            return
        }

        val message = args.joinToString(" ")
        val author = playerManager.getPlayer(sender)

        val senderMessage = ChatMessage(
            author,
            recipient,
            message,
            author.selectMessageFormat(
                config[Settings.PRIVATE_MESSAGES].senderFormats,
                plugin.formatsConfig,
                DEFAULT_PM_SENDER
            )
        )

        val recipientMessage = ChatMessage(
            author,
            recipient,
            message,
            recipient.selectMessageFormat(
                config[Settings.PRIVATE_MESSAGES].recipientFormats,
                plugin.formatsConfig,
                DEFAULT_PM_RECIPIENT
            )
        )

        author.sendMessage(senderMessage)
        recipient.sendMessage(recipientMessage)

        recipient.replyTarget = author.uuid
        println(recipient)
    }

}