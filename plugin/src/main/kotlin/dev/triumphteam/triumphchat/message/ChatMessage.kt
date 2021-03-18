package dev.triumphteam.triumphchat.message

import dev.triumphteam.triumphchat.api.ChatUser
import dev.triumphteam.triumphchat.config.bean.objects.FormatDisplay

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
