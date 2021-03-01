package me.mattstudios.triumphchat.commands

import me.mattstudios.mf.annotations.Alias
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.func.sendPrivateMessage
import me.mattstudios.triumphchat.func.sendTo
import me.mattstudios.triumphchat.locale.Messages
import me.mattstudios.triumphchat.permissions.Permission
import org.bukkit.entity.Player

@Command("reply")
@Alias("r")
class ReplyCommand(private val plugin: TriumphChat) : CommandBase() {

    private val userManager = plugin.userManager
    private val config = plugin.config

    @Default
    @me.mattstudios.mf.annotations.Permission(Permission.COMMAND_REPLY)
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

        userManager.getUser(replyTarget).sendPrivateMessage(author, args.joinToString(" "), plugin)
    }

}