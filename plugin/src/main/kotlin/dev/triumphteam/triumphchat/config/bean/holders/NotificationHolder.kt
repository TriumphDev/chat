package dev.triumphteam.triumphchat.config.bean.holders

import dev.triumphteam.triumphchat.config.bean.objects.elements.SoundData

/**
 * Holds settings regarding notifications
 */
data class NotificationHolder(
    var enabled: Boolean = true,
    var sound: SoundData = SoundData()
)