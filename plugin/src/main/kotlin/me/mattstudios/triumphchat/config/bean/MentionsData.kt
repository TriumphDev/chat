package me.mattstudios.triumphchat.config.bean

import me.mattstudios.triumphchat.config.bean.objects.FormattedDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.MentionDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.SoundData
import java.util.Optional


data class MentionsData(
    var everyone: MentionDisplay = MentionDisplay(
        true,
        FormattedDisplay("&c**@Everyone**"),
        SoundData(),
        Optional.of("%player% has mentioned everyone")
    ),

    var user: MentionDisplay = MentionDisplay(
        true,
        FormattedDisplay("&a__%username%__"),
        SoundData(),
        Optional.of("%player% has mentioned you")
    ),

    var group: MentionDisplay = MentionDisplay(
        true,
        FormattedDisplay("&d**@%group%**"),
        SoundData(),
        Optional.of("%player% has mentioned the group %group%")
    )
)