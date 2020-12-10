package me.mattstudios.triumphchat.config.bean

import me.mattstudios.triumphchat.config.bean.objects.FormatComponent

data class ChatFormat(
        var priority: Int = 1,
        var components: Map<String, FormatComponent> = emptyMap()
)