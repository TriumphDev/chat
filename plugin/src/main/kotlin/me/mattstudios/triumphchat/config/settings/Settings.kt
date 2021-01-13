package me.mattstudios.triumphchat.config.settings

import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Comment
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.properties.Property
import me.mattstudios.triumphchat.config.bean.ChatHolder
import me.mattstudios.triumphchat.config.bean.MentionsHolder
import me.mattstudios.triumphchat.config.bean.NotificationHolder
import me.mattstudios.triumphchat.config.bean.PrivateMessageHolder
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER

/**
 * Main setting holder for all the config related settings
 */
object Settings : SettingsHolder {

    @Path("chat")
    val CHAT_FORMATS = Property.create(ChatHolder())

    @Comment(
        "",
        "Format that will be sent to the console, use $MESSAGE_PLACEHOLDER to represent the message that'll be sent.",
        "Due to console limitations, formats and RGB are not supported here, only basic colors."
    )
    @Path("console-format")
    val CONSOLE_FORMAT = Property.create("[%vault_prefix%] %player_name% > $MESSAGE_PLACEHOLDER")

    @Comment(
        "",
        "Base settings for all the notifications.",
        "Currently only sound."
        // TODO add more
    )
    @Path("notification")
    val NOTIFICATION = Property.create(NotificationHolder())

    @Comment(
        "",
        // TODO Better comment
        "Settings customizing the mentions."
    )
    @Path("mentions")
    val MENTIONS = Property.create(MentionsHolder())

    @Comment(
        "",
        "Customize your private messages."
    )
    @Path("private-messages")
    val PRIVATE_MESSAGES = Property.create(PrivateMessageHolder())

}