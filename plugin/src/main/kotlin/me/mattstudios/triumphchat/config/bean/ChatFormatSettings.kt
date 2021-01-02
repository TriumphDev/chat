package me.mattstudios.triumphchat.config.bean

import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay

data class ChatFormatData(
    var priority: Int = 1,
    var components: Map<String, FormatDisplay> = emptyMap()
)