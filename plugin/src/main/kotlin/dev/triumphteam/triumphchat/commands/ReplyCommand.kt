package dev.triumphteam.triumphchat.commands

import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.func.sendPrivateMessage
import dev.triumphteam.triumphchat.func.sendTo
import dev.triumphteam.triumphchat.locale.Message
import dev.triumphteam.triumphchat.permissions.Permissions
import me.mattstudios.mf.annotations.Alias
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.annotations.Permission
import me.mattstudios.mf.base.CommandBase
import org.bukkit.entity.Player

@Command("reply")
@Alias("r")
class ReplyCommand(private val plugin: TriumphChat) : CommandBase() {

    private val userManager = plugin.userManager
    private val config = plugin.config

    @Default
    @Permission(Permissions.COMMAND_REPLY)
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
            plugin.locale[Message.REPLY_NO_REPLY_TARGET].sendTo(sender)
            return
        }

        userManager.getUser(replyTarget).sendPrivateMessage(author, args.joinToString(" "), plugin)
    }

}