package me.mattstudios.triumphchat.config.bean

import me.mattstudios.triumphchat.config.bean.objects.PingGroup
import java.util.Optional


class PingOptions(
    var everyone: PingGroup = PingGroup(
        true,
        "&c**@Everyone**",
        Optional.of("SOUND"),
        Optional.of("%player% has mentioned everyone")
    ),

    var user: PingGroup = PingGroup(
        true,
        "&a__%username%__",
        Optional.of("SOUND"),
        Optional.of("%player% has mentioned you")
    ),

    var group: PingGroup = PingGroup(
        true,
        "&d**@%group%**&r",
        Optional.of("SOUND"),
        Optional.of("%player% has mentioned the group %group%")
    )
)