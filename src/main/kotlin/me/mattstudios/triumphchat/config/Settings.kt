package me.mattstudios.triumphchat.config

import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Comment
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.configurationdata.CommentsConfiguration
import me.mattstudios.config.properties.Property
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.func.DEFAULT_FORMAT

object Settings : SettingsHolder {

    @Path("test")
    val TEST = Property.create(true)

    @Path("chat.formats")
    val FORMATS = Property.create(ChatFormat::class.java, mutableMapOf("default" to DEFAULT_FORMAT,))

    @Comment("")
    @Path("chat.console-format")
    val CONSOLE_FORMAT = Property.create("[%vault_rank%] %player_name% > %message%")

    override fun registerComments(conf: CommentsConfiguration) {
        conf.setComment("chat", "")
    }

}