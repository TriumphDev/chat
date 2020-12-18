package me.mattstudios.triumphchat.commands

import me.mattstudios.mf.annotations.Alias
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphchat.func.GLOBAL_MESSAGE
import me.mattstudios.triumphchat.func.sendMessage
import org.bukkit.entity.Player

@Command("msg")
@Alias("m")
class MessageCommand(private val temporaryDms: MutableMap<Player, Player>) : CommandBase() {

    @Default
    fun sendMessage(sender: Player, @Completion("#players") to: Player?, @Completion("#empty") args: Array<String>) {
        val target = to ?: return
        val message = args.joinToString(" ")

        target.sendMessage(GLOBAL_MESSAGE.parse(message))
    }

}