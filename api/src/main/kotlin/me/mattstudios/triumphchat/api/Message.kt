package me.mattstudios.triumphchat.api

import net.kyori.adventure.text.Component

/**
 *
 */
interface Message {
    /**
     * The author of this [Message]
     */
    val author: ChatPlayer

    /**
     * The message's [Component] that was generated
     */
    val message: Component

    /**
     * Contains a list of the mentioned [ChatPlayer]s
     */
    val mentionsList: List<ChatPlayer>
}