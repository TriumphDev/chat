package me.mattstudios.triumphchat.config.settings

import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.properties.Property

object Settings : SettingsHolder {

    @Path("check-updates")
    val CHECK_UPDATE = Property.create(true)

}