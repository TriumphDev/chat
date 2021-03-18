package dev.triumphteam.triumphchat.config

import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.config.settings.FormatSetting
import dev.triumphteam.triumphchat.func.PROPERTY_MAPPER
import me.mattstudios.config.SettingsManager
import java.io.File

class FormatsConfig(plugin: TriumphChat) {

    private val config = SettingsManager
            .from(File(plugin.dataFolder, "formats.yml"))
            .propertyMapper(PROPERTY_MAPPER)
            .configurationData(FormatSetting::class.java)
            .create()

    fun getFormats() = config[FormatSetting.FORMATS].formats

    fun reload() = config.reload()

}