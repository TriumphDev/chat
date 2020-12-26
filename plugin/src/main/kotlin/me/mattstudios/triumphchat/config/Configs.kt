package me.mattstudios.triumphchat.config

import me.mattstudios.config.SettingsManager
import me.mattstudios.triumphchat.TriumphChat
import me.mattstudios.triumphchat.config.bean.mapper.SettingsMapper
import me.mattstudios.triumphchat.config.settings.FormatSettings
import me.mattstudios.triumphchat.config.settings.MentionSettings
import java.io.File

class Configs(plugin: TriumphChat) {

    val formats = SettingsManager.from(File(plugin.dataFolder, "formats.yml"))
            .configurationData(FormatSettings::class.java)
            .propertyMapper(MAPPER)
            .create()

    val mentions = SettingsManager.from(File(plugin.dataFolder, "mentions.yml"))
            .configurationData(MentionSettings::class.java)
            .propertyMapper(MAPPER)
            .create()

    /**
     * Reloads the config
     */
    fun reload() {
        formats.reload()
        mentions.reload()
    }


    companion object {
        private val MAPPER = SettingsMapper()
    }

}