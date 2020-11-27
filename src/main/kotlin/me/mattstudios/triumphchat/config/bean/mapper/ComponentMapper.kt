package me.mattstudios.triumphchat.config.bean.mapper

import me.mattstudios.config.annotations.TargetObject
import me.mattstudios.config.beanmapper.PropertyMapper
import me.mattstudios.triumphchat.config.bean.objects.BaseComponent
import me.mattstudios.triumphchat.config.bean.objects.Click
import me.mattstudios.triumphchat.config.bean.objects.FormatComponent
import me.mattstudios.triumphchat.config.bean.objects.MessageComponent
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import java.util.Optional

class ComponentMapper : PropertyMapper {

    /**
     * Maps the config contents into the correct object
     */
    @TargetObject(FormatComponent::class)
    fun mapComponent(data: Map<String, Any>): FormatComponent? {
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

            if (type == null || value == null) Optional.empty<Click>()
            else Optional.of(Click(type.toString(), value.toString()))
        } else {
            Optional.empty<Click>()
        }

        if (MESSAGE_PLACEHOLDER in text) return MessageComponent(text, hover, click)

        return BaseComponent(text, hover, click)
    }

}