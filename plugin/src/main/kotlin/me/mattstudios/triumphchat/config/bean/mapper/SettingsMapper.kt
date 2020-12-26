package me.mattstudios.triumphchat.config.bean.mapper

import me.mattstudios.config.annotations.TargetObject
import me.mattstudios.config.beanmapper.PropertyMapper
import me.mattstudios.triumphchat.config.bean.objects.BaseDisplay
import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay
import me.mattstudios.triumphchat.config.bean.objects.PlaceholderDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import java.util.Optional

class SettingsMapper : PropertyMapper {

    /**
     * Maps a FormatDisplay into the correct implementation
     */
    @TargetObject(FormatDisplay::class)
    fun mapFormatDisplay(path: String, data: Map<String, Any>): FormatDisplay {
        val rawText = data["text"]
        val text = if (rawText is String) rawText else ""

        val hover = createHover(data["hover"])
        val click = createClick(data["click"])

        if (MESSAGE_PLACEHOLDER in text) return PlaceholderDisplay(text, hover, click, MESSAGE_PLACEHOLDER)
        return BaseDisplay(text, hover, click)
    }

    /**
     * This is used to override the property mapper through setter, to allow the formatter to work
     */
    @TargetObject(PlaceholderDisplay::class)
    fun mapMessageDisplay(path: String, data: Map<String, Any>): PlaceholderDisplay {
        val rawText = data["text"]
        val text = if (rawText is String) rawText else ""
        return PlaceholderDisplay(text,  createHover(data["hover"]), createClick(data["click"]))
    }

    /**
     * Creates a hover from the map data
     */
    private fun createHover(rawHover: Any?): Optional<List<String>> {
        return if (rawHover is List<*>) {
            @Suppress("UNCHECKED_CAST")
            Optional.of<List<String>>(rawHover as List<String>)
        } else {
            Optional.empty<List<String>>()
        }
    }

    /**
     * Creates a click from the map data
     */
    private fun createClick(rawClick: Any?): Optional<ClickData> {
        return if (rawClick is Map<*, *>) {
            val type = rawClick["type"]
            val value = rawClick["value"]

            if (type == null || value == null) Optional.empty<ClickData>()
            else Optional.of(ClickData(type.toString(), value.toString()))
        } else {
            Optional.empty<ClickData>()
        }
    }

}