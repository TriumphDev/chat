package me.mattstudios.triumphchat.config.bean

import me.mattstudios.triumphchat.config.bean.objects.Click
import java.util.Optional


class PingOptions(
    var display: String = "<#378cdb>__{ping}__",
    var hover: Optional<List<String>> = Optional.empty<List<String>>(),
    var click: Optional<Click> = Optional.empty()
)