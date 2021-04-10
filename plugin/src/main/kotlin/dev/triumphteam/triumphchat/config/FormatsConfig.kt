package dev.triumphteam.triumphchat.config

import dev.triumphteam.core.TriumphPlugin
import dev.triumphteam.core.configuration.Config
import dev.triumphteam.core.configuration.ConfigFactory
import dev.triumphteam.triumphchat.config.settings.FormatSetting
import dev.triumphteam.triumphchat.func.PROPERTY_MAPPER
import java.io.File
import java.nio.file.Path

class FormatsConfig(dataFolder: File) : Config(
    Path.of(dataFolder.path, "formats.yml"),
    FormatSetting::class.java,
    PROPERTY_MAPPER
) {

    fun getFormats() = config[FormatSetting.FORMATS].formats

    companion object : ConfigFactory {
        override fun create(plugin: TriumphPlugin): Config = FormatsConfig(plugin.dataFolder)
    }

}