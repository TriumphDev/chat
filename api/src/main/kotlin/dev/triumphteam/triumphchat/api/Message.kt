package dev.triumphteam.triumphchat.api

import net.kyori.adventure.text.Component

/**
 *
 */
interface Message {
    /**
     * The author of this [Message]
     */
    val author: ChatUser

    /**
     * The message's [Component] that was generated
     */
    val component: Component

    /**
     * Contains a list of the mentioned [ChatUser]s
     */
    val mentionsList: List<ChatUser>
    
}