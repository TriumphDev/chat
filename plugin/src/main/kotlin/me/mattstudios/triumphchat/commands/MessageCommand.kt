package me.mattstudios.triumphchat.commands

import me.mattstudios.mf.annotations.Alias
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.ChatPlayer
import org.bukkit.entity.Player

@Command("msg")
@Alias("m")
class MessageCommand(plugin: TriumphChat) : CommandBase() {

    private val playerManager = plugin.playerManager
    private val messageManager = plugin.messageManager
    private val config = plugin.config

    @Default
    fun sendMessage(
        sender: Player,
        @Completion("#players") to: ChatPlayer?,
        @Completion("#empty") args: Array<String>
    ) {
        if (to == null) {
            sender.sendMessage("Temp error")
            return
        }

        val author = playerManager.getPlayer(sender)
        println(to)
    }

}