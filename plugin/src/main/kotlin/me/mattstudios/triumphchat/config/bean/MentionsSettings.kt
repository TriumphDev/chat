package me.mattstudios.triumphchat.config.bean

import me.mattstudios.triumphchat.config.bean.objects.FormatGroup
import me.mattstudios.triumphchat.config.bean.objects.MessageDisplay
import me.mattstudios.triumphchat.config.bean.objects.elements.MentionDisplay
import java.util.Optional


class MentionsSettings(
    var everyone: MentionDisplay = MentionDisplay(
        true,
        MessageDisplay("&c**@Everyone**"),
        Optional.of("SOUND"),
        Optional.of("%player% has mentioned everyone")
    ),

    var user: FormatGroup = FormatGroup(
        true,
        "&a__%username%__",
        Optional.of("SOUND"),
        Optional.of("%player% has mentioned you")
    ),

    var group: FormatGroup = FormatGroup(
        true,
        "&d**@%group%**&r",
        Optional.of("SOUND"),
        Optional.of("%player% has mentioned the group %group%")
    )
)