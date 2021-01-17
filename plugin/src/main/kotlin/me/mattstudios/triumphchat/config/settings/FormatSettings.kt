package me.mattstudios.triumphchat.config.settings

import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.properties.Property
import me.mattstudios.triumphchat.config.bean.holders.FormatsHolder

/**
 * Setting holder for the formats config
 */
object FormatSettings : SettingsHolder {

    /**
     * This property is left without a path to allow for a map of a map
     */
    @Path("")
    val FORMATS = Property.create(FormatsHolder())

}