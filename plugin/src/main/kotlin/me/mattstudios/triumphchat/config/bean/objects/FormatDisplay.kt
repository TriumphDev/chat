package me.mattstudios.triumphchat.config.bean.objects

import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData

interface FormatDisplay {
    val text: String
    val hover: List<String>
    val click: ClickData
}