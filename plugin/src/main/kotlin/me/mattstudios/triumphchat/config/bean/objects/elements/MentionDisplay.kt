package me.mattstudios.triumphchat.config.bean.objects.elements

import me.mattstudios.triumphchat.config.bean.objects.PlaceholderDisplay
import java.util.Optional

data class MentionDisplay(
    var enabled: Boolean = true,
    var display: PlaceholderDisplay = PlaceholderDisplay(),
    var sound: SoundData = SoundData(),
    var actionbar: Optional<String> = Optional.empty()
)
