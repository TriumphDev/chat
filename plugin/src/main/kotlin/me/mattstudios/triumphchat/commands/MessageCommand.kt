package me.mattstudios.triumphchat.commands

import me.mattstudios.mf.annotations.Alias
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.base.CommandBase
import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.data.PlayerManager
import me.mattstudios.triumphchat.message.MessageManager
import org.bukkit.entity.Player

@Command("msg")
@Alias("m")
class MessageCommand(
    private val playerManager: PlayerManager,
    private val messageManager: MessageManager
) : CommandBase() {

    @Default
    fun sendMessage(player: Player, @Completion("#players") to: ChatPlayer?, @Completion("#empty") args: Array<String>) {
        val author = playerManager.getPlayer(player)
        if (to == null) {
            player.sendMessage("Temp error")
            return
        }

        println(to)
    }

}