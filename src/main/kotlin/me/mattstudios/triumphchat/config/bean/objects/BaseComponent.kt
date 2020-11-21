package me.mattstudios.triumphchat.config.bean.objects

import java.util.Optional

data class BaseComponent(
    override var text: String = "",
    override var hover: Optional<List<String>> = Optional.empty<List<String>>(),
    override var click: Optional<Click> = Optional.empty()
) : FormatComponent