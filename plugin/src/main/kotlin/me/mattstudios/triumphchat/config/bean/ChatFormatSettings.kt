package me.mattstudios.triumphchat.config.bean

import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay

/**
 * Holds the chat formats with a priority and it's components
 */
data class ChatFormatSettings(
    var priority: Int = 1,
    var components: Map<String, FormatDisplay> = emptyMap()
)