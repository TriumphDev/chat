package me.mattstudios.triumphchat.config

import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.configurationdata.CommentsConfiguration
import me.mattstudios.config.properties.Property
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.PingOptions
import me.mattstudios.triumphchat.func.DEFAULT_FORMAT
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER

object Settings : SettingsHolder {

    @Path("test")
    val TEST = Property.create(true)

    @Path("chat.formats")
    val FORMATS = Property.create(ChatFormat::class.java, mutableMapOf("default" to DEFAULT_FORMAT,))

    @Path("console.format")
    val CONSOLE_FORMAT = Property.create("[%vault_prefix%] %player_name% > $MESSAGE_PLACEHOLDER")

    @Path("chat.ping")
    val PING_OPTIONS = Property.create(PingOptions())

    override fun registerComments(conf: CommentsConfiguration) {
        conf.setComment("chat", "")
        conf.setComment("console", "")
    }

}