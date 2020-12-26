package me.mattstudios.triumphchat.config.bean.objects.elements

import me.mattstudios.triumphchat.config.bean.objects.FormattedDisplay
import java.util.Optional

data class MentionDisplay(
    var enabled: Boolean = true,
    var display: FormattedDisplay = FormattedDisplay(),
    var sound: SoundData = SoundData(),
    var actionbar: Optional<String> = Optional.empty()
)
