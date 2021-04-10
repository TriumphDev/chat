package dev.triumphteam.triumphchat.config.bean.mapper

import dev.triumphteam.triumphchat.config.bean.objects.BaseDisplay
import dev.triumphteam.triumphchat.config.bean.objects.FormatDisplay
import dev.triumphteam.triumphchat.config.bean.objects.MessageDisplay
import dev.triumphteam.triumphchat.config.bean.objects.elements.ClickData
import dev.triumphteam.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.config.annotations.TargetObject
import me.mattstudios.config.beanmapper.PropertyMapper
import java.util.Optional

/**
 * Setting mapper is used to make it easier to identify between interface implementations of [FormatDisplay]
 * And also to allow override default property mapper of complex properties
 */
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

        if (MESSAGE_PLACEHOLDER in text) return MessageDisplay(text, hover, click)
        return BaseDisplay(text, hover, click)
    }

    /**
     * This is used to override the property mapper through setter, to allow the formatter to work
     */
    @TargetObject(MessageDisplay::class)
    fun mapMessageDisplay(path: String, data: Map<String, Any>): MessageDisplay {
        val rawText = data["text"]
        val text = if (rawText is String) rawText else ""
        return MessageDisplay(text, createHover(data["hover"]), createClick(data["click"]))
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