package me.mattstudios.triumphchat.config.bean.objects

import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData

/**
 * Interface for having both [BaseDisplay] and [MessageDisplay] together in the components of the formats
 */
interface FormatDisplay {
    val text: String
    val hover: List<String>
    val click: ClickData
}