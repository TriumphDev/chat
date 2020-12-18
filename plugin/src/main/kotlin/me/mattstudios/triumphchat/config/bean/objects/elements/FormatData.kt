package me.mattstudios.triumphchat.config.bean.objects.elements

import me.mattstudios.msg.base.internal.color.FlatColor
import me.mattstudios.msg.base.internal.color.MessageColor

data class FormatData(
    val color: MessageColor = FlatColor("white"),
    val bold: Boolean = false,
    val italic: Boolean = false,
    val strike: Boolean = false,
    val underlined: Boolean = false,
    val obfuscated: Boolean = false
)