package me.mattstudios.triumphchat.config.bean.mapper

import me.mattstudios.config.annotations.TargetObject
import me.mattstudios.config.beanmapper.PropertyMapper
import me.mattstudios.triumphchat.config.bean.objects.BaseDisplay
import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay
import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
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

        if (MESSAGE_PLACEHOLDER in text) return MessageDisplay(text, hover, click, MESSAGE_PLACEHOLDER)
        return BaseDisplay(text, hover, click)
    }

    @TargetObject(MessageDisplay::class)
    fun mapMessageDisplay(path: String, data: Map<String, Any>): MessageDisplay {
        val rawText = data["text"]
        val text = if (rawText is String) rawText else ""
        return MessageDisplay(text,  createHover(data["hover"]), createClick(data["click"]))
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