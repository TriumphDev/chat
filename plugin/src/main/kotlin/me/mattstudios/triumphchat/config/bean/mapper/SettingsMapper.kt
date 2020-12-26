package me.mattstudios.triumphchat.config.bean.mapper

import me.mattstudios.config.annotations.TargetObject
import me.mattstudios.config.beanmapper.PropertyMapper
import me.mattstudios.triumphchat.config.bean.objects.FormatedDisplay
import me.mattstudios.triumphchat.func.createClick
import me.mattstudios.triumphchat.func.createHover

class SettingsMapper : PropertyMapper {

    /**
     * This is used to override the property mapper through setter, to allow the formatter to work
     */
    @TargetObject(FormatedDisplay::class)
    fun mapMessageDisplay(path: String, data: Map<String, Any>): FormatedDisplay {
        val rawText = data["text"]
        val text = if (rawText is String) rawText else ""
        return FormatedDisplay(text,  createHover(data["hover"]), createClick(data["click"]))
    }

}