package dev.triumphteam.triumphchat.init.checks

import dev.triumphteam.core.func.Checker
import dev.triumphteam.core.func.log
import dev.triumphteam.triumphchat.TriumphChat
import org.bukkit.Bukkit

/**
 * Object for handling dependency checks
 */
object Dependencies : Checker<TriumphChat> {

    /**
     * Checks for plugin dependencies, most important one is PAPI
     * @param plugin Instance of the plugin's main class
     */
    override fun check(plugin: TriumphChat): Boolean = with(plugin) {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            "&cPlaceholderAPI is required to use this Plugin!".log()
            pluginLoader.disablePlugin(this)
            return false
        }

        "&aHooked into PlaceholderAPI successfully!".log()
        return true
    }

}