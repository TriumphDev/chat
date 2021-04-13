/*
 * MIT License
 *
 * Copyright (c) 2021 Mateus Moreira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.triumphteam.triumphchat.listeners

import dev.triumphteam.triumphchat.api.events.TriumphChatEvent
import dev.triumphteam.triumphchat.config.Config
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

    private val config = plugin.config<Config>()

    /**
     * Listens to the AsyncPlayerChatEvent
     */
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    fun AsyncPlayerChatEvent.onPlayerChat() {
        isCancelled = true

        if (!isAsynchronous) {
            //async { handleChat() }
            return
        }

        handleChat()
    }

    /**
     * Handles chat event truly async
     */
    private fun AsyncPlayerChatEvent.handleChat() {
        val user = plugin.userManager.getUser(player)

        val chatFormat =
            user.selectMessageFormat(config[Setting.CHAT_FORMATS].formats, plugin.config(), DEFAULT_FORMAT)

        val chatMessage = ChatMessage(user, message, chatFormat)
        val consoleMessage = ConsoleMessage(plugin, user, message)

        val triumphChatEvent = TriumphChatEvent(chatMessage)
        Bukkit.getPluginManager().callEvent(triumphChatEvent)

        if (triumphChatEvent.isCancelled) return
        recipients.forEach { AUDIENCES.player(it).sendMessage(chatMessage.component) }
        //Bukkit.getConsoleSender().sendMessage(PlainComponentSerializer.INSTANCE.serialize(consoleMessage.component))
    }

}