package me.mattstudios.triumphchat.message

import me.mattstudios.triumphchat.api.ChatUser
import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay

class ChatMessage(
    author: ChatUser,
    recipient: ChatUser?,
    rawMessage: String,
    components: Collection<FormatDisplay>
) : AbstractMessage(
    author,
    recipient,
    rawMessage,
    components
) {

    constructor(author: ChatUser, rawMessage: String, components: Collection<FormatDisplay>) : this(
        author,
        null,
        rawMessage,
        components
    )

}
