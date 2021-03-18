package dev.triumphteam.triumphchat.config.settings

import dev.triumphteam.triumphchat.config.bean.holders.FormatsHolder
import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.properties.Property

/**
 * Setting holder for the formats config
 */
object FormatSetting : SettingsHolder {

    /**
     * This property is left without a path to allow for a map of a map
     */
    @Path("")
    val FORMATS = Property.create(FormatsHolder())

}