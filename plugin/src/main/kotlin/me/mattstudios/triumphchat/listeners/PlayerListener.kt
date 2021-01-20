package me.mattstudios.triumphchat.listeners

import me.mattstudios.triumphchat.managers.UserManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class PlayerListener(private val userManager: UserManager) : Listener {

    @EventHandler
    fun PlayerJoinEvent.onPlayerJoin() {
        userManager.getUser(player)
    }

}