package dev.triumphteam.triumphchat.message

import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.api.ChatUser
import dev.triumphteam.triumphchat.config.bean.objects.MessageDisplay
import dev.triumphteam.triumphchat.config.settings.Setting

class ConsoleMessage(
    plugin: TriumphChat,
    author: ChatUser,
    rawMessage: String
) : AbstractMessage(author, null, rawMessage, listOf(MessageDisplay(plugin.config[Setting.CONSOLE_FORMAT])))