package me.mattstudios.triumphchat.listeners

import me.mattstudios.triumphchat.data.PlayerManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListener(private val playerManager: PlayerManager) : Listener {

    @EventHandler
    fun PlayerJoinEvent.onPlayerJoin() {
        playerManager.addPlayer(player)
    }

}