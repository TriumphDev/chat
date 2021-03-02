package me.mattstudios.triumphchat.commands

import me.mattstudios.mf.annotations.Alias
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.ChatUser
import me.mattstudios.triumphchat.func.sendPrivateMessage
import me.mattstudios.triumphchat.func.sendTo
import me.mattstudios.triumphchat.locale.Messages
import me.mattstudios.triumphchat.permissions.Permission
import org.bukkit.entity.Player

@Command("msg")
@Alias("m")
class MessageCommand(private val plugin: TriumphChat) : CommandBase() {

    private val userManager = plugin.userManager
    private val config = plugin.config
    private val locale = plugin.locale

    @Default
    @me.mattstudios.mf.annotations.Permission(Permission.COMMAND_MESSAGE)
    fun sendMessage(
        sender: Player,
        @Completion("#players") recipient: ChatUser?,
        @Completion("#empty") args: Array<String>
    ) {

        if (recipient == null) {
            locale[Messages.MESSAGE_RECIPIENT_NO_EXIST].sendTo(sender)
            return
        }

        recipient.sendPrivateMessage(userManager.getUser(sender), args.joinToString(" "), plugin)
    }

}