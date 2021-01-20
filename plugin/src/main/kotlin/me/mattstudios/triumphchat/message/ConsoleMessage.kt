package me.mattstudios.triumphchat.message

import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.api.ChatUser
import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
import me.mattstudios.triumphchat.config.settings.Setting

class ConsoleMessage(
    plugin: TriumphChat,
    author: ChatUser,
    rawMessage: String
) : AbstractMessage(author, null, rawMessage, listOf(MessageDisplay(plugin.config[Setting.CONSOLE_FORMAT])))