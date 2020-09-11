package me.mattstudios.triumphchat.config.bean

import me.mattstudios.triumphchat.config.bean.objects.Component

/**
 * @author Matt
 */
data class Format(
        var priority: Int = 1,
        var components: Map<String, Component> = emptyMap()
)