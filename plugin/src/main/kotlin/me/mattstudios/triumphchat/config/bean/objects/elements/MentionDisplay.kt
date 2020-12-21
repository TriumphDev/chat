package me.mattstudios.triumphchat.config.bean.objects.elements

import me.mattstudios.triumphchat.config.bean.objects.FormatedDisplay
import java.util.Optional

data class MentionDisplay(
    var enabled: Boolean = true,
    var display: FormatedDisplay = FormatedDisplay(),
    var sound: SoundData = SoundData(),
    var actionbar: Optional<String> = Optional.empty()
)
