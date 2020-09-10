package me.mattstudios.triumphchat

import io.papermc.lib.PaperLib
import me.mattstudios.core.TriumphPlugin
import me.mattstudios.core.func.log
import me.mattstudios.core.util.Task.async
import me.mattstudios.mfmsg.base.Message
import me.mattstudios.triumphchat.config.Settings
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

/**
 * @author Matt
 */
class TriumphChat : TriumphPlugin(), Listener {

    override fun enable() {
        config.load(Settings::class.java)

        displayStartupMessage()
        if (!checkPapi()) return

        Bukkit.getPluginManager().registerEvents(this, this)
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

    @EventHandler
    fun AsyncPlayerChatEvent.onChat() {
        isCancelled = true

        async {
            testChat(message, recipients, player)
        }
    }

    private fun testChat(rawMessage: String, recipients: Set<Player>, player: Player) {
        val finalMessage = buildString {
            append("**<#e74c3c>Admin** <#3498db>")
            append(player.name)
            append("<#34495e> **»** &r")
            append(rawMessage)
        }

        val message = Message.create()
        val component = message.parse(finalMessage)
        recipients.forEach { component.sendMessage(it) }
    }

}