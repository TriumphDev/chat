package me.mattstudios.triumphchat.commands

import me.mattstudios.mf.annotations.Alias
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.chat.ChatMessage
import me.mattstudios.triumphchat.config.settings.Settings
import me.mattstudios.triumphchat.func.DEFAULT_PM_RECIPIENT
import me.mattstudios.triumphchat.func.DEFAULT_PM_SENDER
import me.mattstudios.triumphchat.func.selectMessageFormat
import org.bukkit.entity.Player

@Command("reply")
@Alias("r")
class ReplyCommand(private val plugin: TriumphChat) : CommandBase() {

    private val playerManager = plugin.userManager
    private val config = plugin.config

    @Default
    fun sendReply(
        sender: Player,
        args: Array<String>
    ) {
        val author = playerManager.getPlayer(sender)
        println(author)
        val replyTarget = author.replyTarget

        if (replyTarget == null) {
            sender.sendMessage("No reply recipient")
            return
        }

        val recipient = playerManager.getPlayer(replyTarget)

        val messageString = args.joinToString(" ")

        val senderMessage = ChatMessage(
            author,
            recipient,
            messageString,
            author.selectMessageFormat(
                config[Settings.PRIVATE_MESSAGES].senderFormats,
                plugin.formatsConfig,
                DEFAULT_PM_SENDER
            )
        )

        val recipientMessage = ChatMessage(
            author,
            recipient,
            messageString,
            recipient.selectMessageFormat(
                config[Settings.PRIVATE_MESSAGES].recipientFormats,
                plugin.formatsConfig,
                DEFAULT_PM_RECIPIENT
            )
        )

        author.sendMessage(senderMessage)
        recipient.sendMessage(recipientMessage)

        author.replyTarget = recipient.uuid
    }

}