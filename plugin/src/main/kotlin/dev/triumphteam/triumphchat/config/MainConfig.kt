package dev.triumphteam.triumphchat.config

import dev.triumphteam.core.TriumphPlugin
import dev.triumphteam.core.configuration.Config
import dev.triumphteam.core.configuration.ConfigFactory
import dev.triumphteam.triumphchat.config.settings.Setting
import dev.triumphteam.triumphchat.func.PROPERTY_MAPPER
import java.io.File
import java.nio.file.Path

class MainConfig(dataFolder: File) : Config(
    Path.of(dataFolder.path, "config.yml"),
    Setting::class.java,
    PROPERTY_MAPPER
) {

    companion object : ConfigFactory {
        override fun create(plugin: TriumphPlugin): Config = MainConfig(plugin.dataFolder)
    }

}