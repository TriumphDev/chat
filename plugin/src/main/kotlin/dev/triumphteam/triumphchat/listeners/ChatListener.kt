package dev.triumphteam.triumphchat.listeners

import dev.triumphteam.triumphchat.api.events.TriumphChatEvent
import dev.triumphteam.triumphchat.config.FormatsConfig
import dev.triumphteam.triumphchat.config.MainConfig
import dev.triumphteam.triumphchat.config.settings.Setting
import dev.triumphteam.triumphchat.func.AUDIENCES
import dev.triumphteam.triumphchat.func.DEFAULT_FORMAT
import dev.triumphteam.triumphchat.func.selectMessageFormat
import dev.triumphteam.triumphchat.message.ChatMessage
import dev.triumphteam.triumphchat.message.ConsoleMessage
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent

class ChatListener(private val plugin: dev.triumphteam.triumphchat.TriumphChat) : Listener {

    private val config = plugin.config<MainConfig>()

    /**
     * Listens to the AsyncPlayerChatEvent
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun AsyncPlayerChatEvent.onPlayerChat() {
        println(message)
        //isCancelled = true

        if (!isAsynchronous) {
          //  async { handleChat() }
            return
        }

      //  handleChat()
    }

    /**
     * Handles chat event truly async
     */
    private fun AsyncPlayerChatEvent.handleChat() {
        val user = plugin.userManager.getUser(player)

        val chatFormat =
            user.selectMessageFormat(config[Setting.CHAT_FORMATS].formats, plugin.config<FormatsConfig>(), DEFAULT_FORMAT)

        val chatMessage = ChatMessage(user, message, chatFormat)
        val consoleMessage = ConsoleMessage(plugin, user, message)

        val triumphChatEvent = TriumphChatEvent(chatMessage)
        Bukkit.getPluginManager().callEvent(triumphChatEvent)

        if (triumphChatEvent.isCancelled) return
        recipients.forEach { AUDIENCES.player(it).sendMessage(chatMessage.component) }
        //Bukkit.getConsoleSender().sendMessage(PlainComponentSerializer.INSTANCE.serialize(consoleMessage.component))
    }

}