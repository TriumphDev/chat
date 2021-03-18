package dev.triumphteam.triumphchat.config.bean.holders

import dev.triumphteam.triumphchat.config.bean.objects.elements.SubNotificationSettings
import dev.triumphteam.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.config.annotations.Comment
import me.mattstudios.config.annotations.Name
import java.util.Optional

/**
 * Holds the settings for the private messages
 */
data class PrivateMessageHolder(
    @Comment("List of formats to use for the sender.")
    @Name("sender-formats")
    var senderFormats: MutableSet<String> = mutableSetOf("default-pm-sender"),

    @Comment("List of formats to use for the recipient.")
    @Name("recipient-formats")
    var recipientFormats: MutableSet<String> = mutableSetOf("default-pm-recipient"),

    var notification: SubNotificationSettings = SubNotificationSettings(optionalActions = Optional.of(listOf("[actionbar] &cYou have received a private message!"))),

    @Comment("Single format to use for the social spy.")
    @Name("social-spy-format")
    var socialSpyFormat: String = "&8[&cSpy&8] &f%sender_player_name% &e-> &f%recipient_player_name%&7: &f$MESSAGE_PLACEHOLDER"
)