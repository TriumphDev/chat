package me.mattstudios.triumphchat.config

import me.mattstudios.config.SettingsManager
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.config.settings.FormatSetting
import me.mattstudios.triumphchat.func.PROPERTY_MAPPER
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