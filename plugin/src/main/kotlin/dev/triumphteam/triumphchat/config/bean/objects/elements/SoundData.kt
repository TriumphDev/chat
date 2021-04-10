package dev.triumphteam.triumphchat.config.bean.objects.elements

import org.bukkit.Sound

/**
 * Holds the data regarding the notification sounds
 */
data class SoundData(
    var enabled: Boolean = true,
    var sound: Sound = Sound.BLOCK_NOTE_BLOCK_BELL,
    var volume: Float = 10.0f,
    var pitch: Float = 1.0f
)
