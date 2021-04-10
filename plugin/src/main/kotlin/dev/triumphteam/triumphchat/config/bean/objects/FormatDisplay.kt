package dev.triumphteam.triumphchat.config.bean.objects

import dev.triumphteam.triumphchat.config.bean.objects.elements.ClickData

/**
 * Interface for having separate components of the formats
 */
interface FormatDisplay {
    val text: String
    val hover: List<String>
    val click: ClickData
}