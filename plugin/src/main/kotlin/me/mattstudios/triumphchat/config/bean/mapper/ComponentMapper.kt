package me.mattstudios.triumphchat.config.bean.mapper

import me.mattstudios.config.annotations.TargetObject
import me.mattstudios.config.beanmapper.PropertyMapper
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.triumphchat.config.bean.objects.FormatDisplay
import me.mattstudios.triumphchat.config.bean.objects.BaseDisplay
import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import java.util.Optional

class ComponentMapper : PropertyMapper {

    /**
     * Maps the config contents into the correct object
     */
    @TargetObject(FormatDisplay::class)
    fun mapComponent(data: Map<String, Any>): FormatDisplay {
        val rawText = data["text"]

        val rawHover = data["hover"]
        val rawClick = data["click"]

        val text = if (rawText is String) rawText else ""

        val hover = if (rawHover is List<*>) {
            Optional.of<List<String>>(rawHover as List<String>)
        } else {
            Optional.empty<List<String>>()
        }

        val click = if (rawClick is Map<*, *>) {
            val type = rawClick["type"]
            val value = rawClick["value"]

            if (type == null || value == null) Optional.empty<ClickData>()
            else Optional.of(ClickData(type.toString(), value.toString()))
        } else {
            Optional.empty<ClickData>()
        }

        if (MESSAGE_PLACEHOLDER in text) return MessageDisplay(text, hover, click)

        return BaseDisplay(text, hover, click)
    }

}