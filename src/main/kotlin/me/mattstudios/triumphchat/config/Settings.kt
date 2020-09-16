package me.mattstudios.triumphchat.config

import ch.jalu.configme.SettingsHolder
import ch.jalu.configme.properties.Property
import ch.jalu.configme.properties.PropertyBuilder.MapPropertyBuilder
import ch.jalu.configme.properties.PropertyInitializer.newProperty
import ch.jalu.configme.properties.types.BeanPropertyType
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.constants.Constant

/**
 * @author Matt
 */
object Settings : SettingsHolder {

    val TEST: Property<Boolean> = newProperty(
        "test", true
    )

    val FORMATS: Property<Map<String, ChatFormat>> =
        MapPropertyBuilder(BeanPropertyType.of(ChatFormat::class.java))
                .path("chat.formats")
                .defaultEntry("default", Constant.DEFAULT_FORMAT)
                .build()

    val CONSOLE_FORMAT: Property<String> =
        newProperty("chat.console-format", "[%vault_rank%] %player_name% > %message%")

}