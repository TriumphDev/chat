package me.mattstudios.triumphchat.config.bean.objects.elements

import org.bukkit.Sound

/**
 * Notification data holds global settings regarding notifications
 */
data class NotificationData(
    var enabled: Boolean = true,
    var sound: Sound = Sound.BLOCK_NOTE_BLOCK_BELL,
    var volume: Float = 10.0f,
    var pitch: Float = 1.0f
)
