package me.mattstudios.triumphchat.config.bean

import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.MentionDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.SoundData
import java.util.Optional


data class MentionsSettings(
    var everyone: MentionDisplay = MentionDisplay(
        true,
        MessageDisplay("&c**@Everyone**"),
        SoundData(),
        Optional.of("%player% has mentioned everyone")
    ),

    var user: MentionDisplay = MentionDisplay(
        true,
        MessageDisplay("&a__%username%__"),
        SoundData(),
        Optional.of("%player% has mentioned you")
    ),

    var group: MentionDisplay = MentionDisplay(
        true,
        MessageDisplay("&d**@%group%**"),
        SoundData(),
        Optional.of("%player% has mentioned the group %group%")
    )
)