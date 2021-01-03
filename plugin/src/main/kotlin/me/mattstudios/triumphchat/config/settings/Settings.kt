package me.mattstudios.triumphchat.config.settings

import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Comment
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.properties.Property
import me.mattstudios.triumphchat.config.bean.MentionsSettings
import me.mattstudios.triumphchat.config.bean.NotificationSettings
import me.mattstudios.triumphchat.config.bean.PrivateMessageSettings
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER

/**
 * Main setting holder for all the config related settings
 */
object Settings : SettingsHolder {

    /*@Comment(
        "You can list as many formats as you want.",
        "Formats have specific components, the components can be anything you want and will be displayed in the order they are declared.",
        "You can use any placeholder from PlaceholderAPI.",
        "Make sure the message placeholder ($MESSAGE_PLACEHOLDER) is present in one of the components!",
        // TODO priority comment
        // TODO Add link to the wiki explaining this better
        "The priority is..."
    )
    @Path("formats")
    val FORMATS = Property.create(ChatFormatSettings::class.java, mutableMapOf("default" to DEFAULT_FORMAT))*/

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
    val NOTIFICATION = Property.create(NotificationSettings())

    @Comment(
        "",
        // TODO Better comment
        "Settings customizing the mentions."
    )
    @Path("mentions")
    val MENTIONS = Property.create(MentionsSettings())

    @Comment(
        "",
        "Customize your private messages."
    )
    @Path("private-messages")
    val PRIVATE_MESSAGES = Property.create(PrivateMessageSettings())

}