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
import me.mattstudios.triumphchat.func.selectFormat
import org.bukkit.entity.Player

@Command("reply")
@Alias("r")
class ReplyCommand(private val plugin: TriumphChat) : CommandBase() {

    private val playerManager = plugin.playerManager
    private val config = plugin.config

    @Default
    fun sendReply(
        sender: Player,
        args: Array<String>
    ) {
        val recipientReplier = playerManager.getRecipientEntry(sender.uniqueId)

        if (recipientReplier == null) {
            sender.sendMessage("No reply recipient")
            return
        }

        val messageString = args.joinToString(" ")
        val author = playerManager.getPlayer(sender)

        val senderMessage = ChatMessage(
            author,
            recipientReplier,
            messageString,
            author.selectFormat(
                config[Settings.PRIVATE_MESSAGES].senderFormats,
                plugin.formatsConfig,
                DEFAULT_PM_SENDER
            )
        )

        val recipientMessage = ChatMessage(
            author,
            recipientReplier,
            messageString,
            recipientReplier.selectFormat(
                config[Settings.PRIVATE_MESSAGES].recipientFormats,
                plugin.formatsConfig,
                DEFAULT_PM_RECIPIENT
            )
        )

        author.sendMessage(senderMessage)
        recipientReplier.sendMessage(recipientMessage)
    }

}