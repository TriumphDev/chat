package me.mattstudios.triumphchat.config.bean

import me.mattstudios.triumphchat.config.bean.objects.FormatedDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.MentionDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.SoundData
import java.util.Optional


data class MentionsSettings(
    var everyone: MentionDisplay = MentionDisplay(
        true,
        FormatedDisplay("&c**@Everyone**"),
        SoundData(),
        Optional.of("%player% has mentioned everyone")
    ),

    var user: MentionDisplay = MentionDisplay(
        true,
        FormatedDisplay("&a__%username%__"),
        SoundData(),
        Optional.of("%player% has mentioned you")
    ),

    var group: MentionDisplay = MentionDisplay(
        true,
        FormatedDisplay("&d**@%group%**"),
        SoundData(),
        Optional.of("%player% has mentioned the group %group%")
    )
)