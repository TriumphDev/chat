package me.mattstudios.triumphchat.chat

import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay

class ConsoleMessage(
    author: ChatPlayer,
    rawMessage: String,
    components: Collection<FormatDisplay>
) : AbstractMessage(author, null, rawMessage, components)