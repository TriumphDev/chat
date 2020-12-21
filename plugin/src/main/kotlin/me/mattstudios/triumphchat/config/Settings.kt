package me.mattstudios.triumphchat.config

import me.mattstudios.config.SettingsHolder
import me.mattstudios.config.annotations.Comment
import me.mattstudios.config.annotations.Path
import me.mattstudios.config.configurationdata.CommentsConfiguration
import me.mattstudios.config.properties.Property
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.MentionsSettings
import me.mattstudios.triumphchat.func.DEFAULT_FORMAT
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER

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

    @Comment(
        "",
        "Configure how mentions will be displayed in chat!",
        "Every mention can be disabled."
    )
    @Path("mentions")
    val MENTIONS = Property.create(MentionsSettings())

    /*@Path("chat.private-message.sender-format")
    val DM_SENDER_FORMAT = Property.create(
        MessageDisplay(
            "&7you &e-> &7{receiver} &7:{message}",
            Optional.of(listOf("%player_name%")),
            Optional.of(ClickData("RUN_COMMAND", "/r"))
        )
    )

    @Path("chat.private-message.receiver-format")
    val DM_RECEIVER_FORMAT = Property.create(
        MessageDisplay(
            "&7{sender} &e-> &7you &7:{message}",
            Optional.of(listOf("%player_name%")),
            Optional.of(ClickData("RUN_COMMAND", "/r"))
        )
    )

    @Path("chat.private-message.social-spy-format")
    val SOCIAL_SPY_FORMAT = Property.create(
        MessageDisplay(
            "&8[&cspy&8] &f{sender} &e-> &f{receiver}&7:",
            Optional.of(listOf("%player_name%")),
            Optional.of(ClickData("RUN_COMMAND", "/r"))
        )
    )
    */
    override fun registerComments(conf: CommentsConfiguration) {
        conf.setComment("chat", "")
        conf.setComment("console", "")
    }

}