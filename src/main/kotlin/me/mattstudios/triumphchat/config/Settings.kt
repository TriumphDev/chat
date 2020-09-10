package me.mattstudios.triumphchat.config

import ch.jalu.configme.SettingsHolder
import ch.jalu.configme.properties.Property
import ch.jalu.configme.properties.PropertyInitializer.newProperty

/**
 * @author Matt
 */
object Settings : SettingsHolder {

    val TEST: Property<Boolean> = newProperty(
        "test", true
    )

}