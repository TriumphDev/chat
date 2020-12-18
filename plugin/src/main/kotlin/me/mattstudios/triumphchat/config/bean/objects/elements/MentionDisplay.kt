package me.mattstudios.triumphchat.config.bean.objects.elements

import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay

data class MentionDisplay(
    var enabled: Boolean = true,
    var display: MessageDisplay = MessageDisplay(),
    var sound: SoundData = SoundData()
    //var actionbar: Optional<String> = Optional.empty()
)
