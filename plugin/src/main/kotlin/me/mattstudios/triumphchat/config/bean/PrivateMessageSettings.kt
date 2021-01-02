package me.mattstudios.triumphchat.config.bean

import me.mattstudios.config.annotations.Name
import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.triumphchat.config.bean.objects.elements.SubNotificationSettings
import me.mattstudios.triumphchat.func.MESSAGE_PLACEHOLDER
import java.util.Optional

/**
 * Holds the settings for the private messages
 */
data class PrivateMessageSettings(
    @Name("sender-format")
    var senderFormat: MessageDisplay = MessageDisplay(
        "&7you &e-> &7%recipient_player_name% &7: &f$MESSAGE_PLACEHOLDER",
        clickData = Optional.of(ClickData("SUGGEST_COMMAND", "/r "))
    ),

    @Name("recipient-format")
    var recipientFormat: MessageDisplay = MessageDisplay(
        "&7%sender_player_name% &e-> &7you &7: &f$MESSAGE_PLACEHOLDER",
        clickData = Optional.of(ClickData("SUGGEST_COMMAND", "/r "))
    ),

    @Name("recipient-notification")
    var notification: SubNotificationSettings = SubNotificationSettings(optionalActions = Optional.of(listOf("[actionbar] &cYou have received a private message!"))),

    @Name("social-spy-format")
    var socialSpyFormat: MessageDisplay = MessageDisplay(
        "&8[&cSpy&8] &f%sender_player_name% &e-> &f%recipient_player_name%&7: &f$MESSAGE_PLACEHOLDER"
    )
)