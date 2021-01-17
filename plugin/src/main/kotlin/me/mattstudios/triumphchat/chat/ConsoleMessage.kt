package me.mattstudios.triumphchat.chat

import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.ChatUser
import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
import me.mattstudios.triumphchat.config.settings.Settings

class ConsoleMessage(
    plugin: TriumphChat,
    author: ChatUser,
    rawMessage: String
) : AbstractMessage(author, null, rawMessage, listOf(MessageDisplay(plugin.config[Settings.CONSOLE_FORMAT])))