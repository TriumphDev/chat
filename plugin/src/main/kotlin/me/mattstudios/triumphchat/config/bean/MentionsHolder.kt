package me.mattstudios.triumphchat.config.bean

import me.mattstudios.triumphchat.config.bean.objects.BaseDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.MentionDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.SubNotificationSettings
import java.util.Optional

/**
 * Holds settings regarding mentions
 */
data class MentionsHolder(
    var everyone: MentionDisplay = MentionDisplay(
        true,
        BaseDisplay("&c**@Everyone**"),
        SubNotificationSettings(optionalActions = Optional.of(listOf("[actionbar] &c%player_name% mentioned everyone!")))
    ),
    var user: MentionDisplay = MentionDisplay(
        true,
        BaseDisplay("&a__%username%__"),
        SubNotificationSettings(optionalActions = Optional.of(listOf("[actionbar] &c%player_name% mentioned you!")))
    ),
    var group: MentionDisplay = MentionDisplay(
        true,
        BaseDisplay("&d**@%group%**"),
        SubNotificationSettings(optionalActions = Optional.of(listOf("[actionbar] &c%player_name% mention the group %group%!")))
    )
)