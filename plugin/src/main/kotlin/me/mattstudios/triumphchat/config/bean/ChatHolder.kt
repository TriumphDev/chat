package me.mattstudios.triumphchat.config.bean

/**
 * Holds settings regarding chat
 */
data class ChatHolder(
    var formats: MutableSet<String> = mutableSetOf("default")
)