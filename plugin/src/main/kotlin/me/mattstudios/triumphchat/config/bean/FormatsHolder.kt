package me.mattstudios.triumphchat.config.bean

import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay
import me.mattstudios.triumphchat.func.DEFAULT_FORMAT_MAP

/**
 * Holds the chat formats with a priority and it's components
 */
data class FormatsHolder(
    var formats: MutableMap<String, MutableMap<String, FormatDisplay>> = DEFAULT_FORMAT_MAP
)