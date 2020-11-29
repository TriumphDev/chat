package me.mattstudios.triumphchat

import me.mattstudios.annotations.BukkitPlugin
import me.mattstudios.core.TriumphPlugin
import me.mattstudios.core.func.log
import me.mattstudios.triumphchat.config.Settings
import me.mattstudios.triumphchat.config.bean.mapper.ComponentMapper
import me.mattstudios.triumphchat.config.bean.objects.MessageComponent
import me.mattstudios.triumphchat.func.isPaper
import me.mattstudios.triumphchat.listeners.ChatListener
import org.bukkit.Bukkit
import org.bukkit.event.Listener

@BukkitPlugin
class TriumphChat : TriumphPlugin(), Listener {

    /**
     * Enable things
     */
    override fun enable() {
        config.load(Settings::class.java, ComponentMapper())

        displayStartupMessage()
        if (!checkPapi()) return
        checkMessageComponents()

        registerListeners(listOf(ChatListener(this)))
    }

    /**
     * Checks if PAPI is enabled and disables plugin if not
     */
    private fun checkPapi(): Boolean {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            "&cPlaceholderAPI is required to use this Plugin!".log()
            pluginLoader.disablePlugin(this)
            return false
        }

        "&aHooked into PlaceholderAPI successfully!".log()
        return true
    }

    /**
     * Displays the startup message of the plugin
     */
    private fun displayStartupMessage() {
        if (isPaper) {
            """
                
            &c█▀▀ █░█ ▄▀█ ▀█▀ &8Version: &c${description.version}
            &c█▄▄ █▀█ █▀█ ░█░ &8By: &cMatt
            
            """.trimIndent().lines().forEach(String::log)
        } else {
            "Go die".log()
        }

    }

    /**
     * Checks if there is any format without a message component
     */
    private fun checkMessageComponents() {
        for ((name, format) in config[Settings.FORMATS]) {
            if (format.components.values.filterIsInstance<MessageComponent>().count() == 0) {
                "&6No component with &7%message% &6placeholder was found for format &7\"$name\"&6.".log()
            }
        }
    }

}