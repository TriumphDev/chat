package me.mattstudios.triumphchat.config.settings

import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Comment
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.configurationdata.CommentsConfiguration
import me.mattstudios.config.properties.Property
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.objects.PlaceholderDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.MentionDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.SoundData
import me.mattstudios.triumphchat.func.DEFAULT_FORMAT
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import java.util.Optional

object Settings : SettingsHolder {

    @Comment(
        "You can list as many formats as you want.",
        "Formats have specific components, the components can be anything you want and will be displayed in the order they are declared.",
        "You can use any placeholder from PlaceholderAPI.",
        "Make sure the message placeholder ($MESSAGE_PLACEHOLDER) is present in one of the components!",
        // TODO priority comment
        // TODO Add link to the wiki explaining this better
        "The priority is..."
    )
    @Path("formats")
    val FORMATS = Property.create(ChatFormat::class.java, mutableMapOf("default" to DEFAULT_FORMAT))

    @Comment(
        "",
        "Format that will be sent to the console, use $MESSAGE_PLACEHOLDER to represent the message that'll be sent.",
        "Due to console limitations, formats and RGB are not supported here, only basic colors."
    )
    @Path("console-format")
    val CONSOLE_FORMAT = Property.create("[%vault_prefix%] %player_name% > $MESSAGE_PLACEHOLDER")

    @Path("mention.everyone")
    val EVERYONE = Property.create(
        MentionDisplay(
            true,
            PlaceholderDisplay("&c**@Everyone**"),
            SoundData(),
            Optional.of("%player% has mentioned everyone")
        )
    )

    @Path("mention.group")
    val GROUP = Property.create(
        MentionDisplay(
            true,
            PlaceholderDisplay("&d**@%group%**"),
            SoundData(),
            Optional.of("%player% has mentioned the group %group%")
        )
    )

    @Path("mention.player")
    val PLAYER = Property.create(
        MentionDisplay(
            true,
            PlaceholderDisplay("&a__%username%__"),
            SoundData(),
            Optional.of("%player% has mentioned you")
        )
    )

    override fun registerComments(conf: CommentsConfiguration) {
        conf.setComment("mention", "")
    }

}