package me.mattstudios.triumphchat.chat

import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay

class ChatMessage(
    author: ChatPlayer,
    recipient: ChatPlayer? = null,
    rawMessage: String,
    components: Collection<FormatDisplay>
) : AbstractMessage(author, recipient, rawMessage, components) {

    constructor(author: ChatPlayer, rawMessage: String, components: Collection<FormatDisplay>) : this(
        author,
        null,
        rawMessage,
        components
    )

}
