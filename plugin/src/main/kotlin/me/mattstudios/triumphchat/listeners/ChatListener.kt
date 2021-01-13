package me.mattstudios.triumphchat.listeners

import me.mattstudios.core.func.Task.async
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.events.TriumphChatEvent
import me.mattstudios.triumphchat.chat.ChatMessage
import me.mattstudios.triumphchat.chat.ConsoleMessage
import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
import me.mattstudios.triumphchat.config.settings.Settings
import me.mattstudios.triumphchat.func.AUDIENCES
import me.mattstudios.triumphchat.func.DEFAULT_FORMAT
import me.mattstudios.triumphchat.func.selectFormat
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatListener(private val plugin: TriumphChat) : Listener {

    private val config = plugin.config

    /**
     * Listens to the AsyncPlayerChatEvent
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun AsyncPlayerChatEvent.onPlayerChat() {
        isCancelled = true

        if (!isAsynchronous) {
            async { handleChat() }
            return
        }

        handleChat()
    }

    /**
     * Handles chat event truly async
     */
    private fun AsyncPlayerChatEvent.handleChat() {
        val chatPlayer = plugin.playerManager.getPlayer(player)

        val chatMessage = ChatMessage(
            chatPlayer,
            message,
            chatPlayer.selectFormat(config[Settings.CHAT_FORMATS].formats, plugin.formatsConfig, DEFAULT_FORMAT)
        )

        val consoleMessage = ConsoleMessage(
            chatPlayer,
            message,
            listOf(MessageDisplay(config[Settings.CONSOLE_FORMAT]))
        )

        val triumphChatEvent = TriumphChatEvent(chatMessage)
        Bukkit.getPluginManager().callEvent(triumphChatEvent)

        if (triumphChatEvent.isCancelled) return

        recipients.forEach { AUDIENCES.player(it).sendMessage(chatMessage.message) }
    }

}