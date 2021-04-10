package dev.triumphteam.triumphchat.commands

import dev.triumphteam.triumphchat.api.ChatUser
import dev.triumphteam.triumphchat.config.MainConfig
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

@Command("msg")
@Alias("m")
class MessageCommand(private val plugin: dev.triumphteam.triumphchat.TriumphChat) : CommandBase() {

    private val userManager = plugin.userManager
    private val config = plugin.config<MainConfig>()
    private val locale = plugin.locale

    @Default
    @Permission(Permissions.COMMAND_MESSAGE)
    fun sendMessage(
        sender: Player,
        @Completion("#players") recipient: ChatUser?,
        @Completion("#empty") args: Array<String>
    ) {
        
        if (recipient == null) {
            locale[Message.MESSAGE_RECIPIENT_NO_EXIST].sendTo(sender)
            return
        }

        recipient.sendPrivateMessage(userManager.getUser(sender), args.joinToString(" "), plugin)
    }

}