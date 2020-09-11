package me.mattstudios.triumphchat.listeners

import me.mattstudios.core.configuration.Config
import me.mattstudios.core.util.Task.async
import me.mattstudios.mfmsg.base.Message
import me.mattstudios.triumphchat.config.Settings
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent


/**
 * @author Matt
 */
class ChatListener(private val config: Config) : Listener {

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    fun AsyncPlayerChatEvent.onPlayerChat() {
        isCancelled = true

        if (!isAsynchronous) {
            async { handleChat() }
            return
        }

        handleChat()
    }

    private fun AsyncPlayerChatEvent.handleChat() {
        val format = config[Settings.FORMATS].entries.first().value

        val sendMessage = buildString {
            /*append(format.prefix)
            append(format.name)
            append(format.suffix)
            append(format.`chat-color`)*/
            append(message)
        }

        Message.create().parse(sendMessage).sendMessage(player)
    }

}