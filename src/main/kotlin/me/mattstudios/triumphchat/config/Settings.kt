package me.mattstudios.triumphchat.config

import ch.jalu.configme.SettingsHolder
import ch.jalu.configme.properties.Property
import ch.jalu.configme.properties.PropertyBuilder.MapPropertyBuilder
import ch.jalu.configme.properties.PropertyInitializer.newProperty
import ch.jalu.configme.properties.types.BeanPropertyType
import me.mattstudios.triumphchat.config.bean.Format
import me.mattstudios.triumphchat.config.bean.objects.ClickAction
import me.mattstudios.triumphchat.config.bean.objects.Component

/**
 * @author Matt
 */
object Settings : SettingsHolder {

    val TEST: Property<Boolean> = newProperty(
            "test", true
    )

    val FORMATS: Property<Map<String, Format>> =
            MapPropertyBuilder(BeanPropertyType.of(Format::class.java))
                    .path("formats")
                    .defaultEntry("default", Format(1, mapOf(
                            "prefix" to Component("%vault_prefix% ", click = ClickAction("RUN_COMMAND", "ranks")),
                            "name" to Component("&f%player_name%", listOf("Click to send a message"), ClickAction("SUGGEST_COMMAND", "/msg %player_name% ")),
                            "message" to Component("&8> &a%message%", listOf("Sent @ %server_time ....%"))
                    )))
                    .build()

}