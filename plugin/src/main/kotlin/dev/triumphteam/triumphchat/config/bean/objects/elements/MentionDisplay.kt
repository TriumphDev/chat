package dev.triumphteam.triumphchat.config.bean.objects.elements

import dev.triumphteam.triumphchat.config.bean.objects.BaseDisplay

/**
 * Holds the data of how the mentions should be displayed
 */
data class MentionDisplay(
    var enabled: Boolean = true,
    var display: BaseDisplay = BaseDisplay(),
    var notification: SubNotificationSettings = SubNotificationSettings()
)
