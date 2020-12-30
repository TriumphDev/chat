package me.mattstudios.triumphchat.chat

import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay
import me.mattstudios.triumphchat.func.sendMessage
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class ConsoleMessage(
    author: ChatPlayer,
    rawMessage: String,
    recipients: Set<Player>,
    plugin: TriumphChat,
    components: Collection<FormatDisplay>
) : ChatMessage(author, rawMessage, recipients, plugin, components) {

    override fun sendMessage() = Bukkit.getConsoleSender().sendMessage(message)

}