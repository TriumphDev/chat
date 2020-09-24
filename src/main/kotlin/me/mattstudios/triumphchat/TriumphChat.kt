package me.mattstudios.triumphchat

import io.papermc.lib.PaperLib
import me.mattstudios.core.TriumphPlugin
import me.mattstudios.core.func.log
import me.mattstudios.triumphchat.config.Settings
import me.mattstudios.triumphchat.listeners.ChatListener
import org.bukkit.Bukkit
import org.bukkit.event.Listener

/**
 * @author Matt
 */
class TriumphChat : TriumphPlugin(), Listener {

    override fun enable() {
        config.load(Settings::class.java)

        displayStartupMessage()
        if (!checkPapi()) return

        registerListeners(listOf(ChatListener(this)))
    }

    private fun checkPapi(): Boolean {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            "&cPlaceholderAPI is required to use this Plugin!".log()
            pluginLoader.disablePlugin(this)
            return false
        }

        "&aHooked into PlaceholderAPI successfully!".log()

        return true
    }

    private fun displayStartupMessage() {
        if (PaperLib.isPaper()) {
            """
                
            &c█▀▀ █░█ ▄▀█ ▀█▀ &8Version: &c1.0
            &c█▄▄ █▀█ █▀█ ░█░ &8By: &cMateus Moreira
            
            """.trimIndent().lines().forEach(String::log)
        } else {
            "Go die".log()
        }
    }

}