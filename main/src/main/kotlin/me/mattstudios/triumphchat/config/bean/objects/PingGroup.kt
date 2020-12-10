package me.mattstudios.triumphchat.config.bean.objects

import java.util.Optional

data class PingGroup(
    var enabled: Boolean = true,
    var text: String = "",
    var sound: Optional<String> = Optional.empty(),
    var actionbar: Optional<String> = Optional.empty()
)
