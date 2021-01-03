package me.mattstudios.triumphchat.config

import me.mattstudios.config.SettingsManager
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.config.settings.FormatSettings
import me.mattstudios.triumphchat.func.PROPERTY_MAPPER
import java.io.File

class FormatsConfig(plugin: TriumphChat) {

    private val config = SettingsManager
            .from(File(plugin.dataFolder, "formats.yml"))
            .propertyMapper(PROPERTY_MAPPER)
            .configurationData(FormatSettings::class.java)
            .create()

    fun getFormats() = config[FormatSettings.FORMATS].formats

    fun reload() = config.reload()

}