package me.mattstudios.triumphchat.config.bean.objects

import java.util.Optional

data class BaseComponent(
    override var text: String = "",
    var hover: Optional<List<String>> = Optional.empty<List<String>>(),
    var click: Optional<Click> = Optional.empty()
) : FormatComponent {

    override val formatHover = if (hover.isPresent) hover.get() else emptyList()
    override val formatClick = if (click.isPresent) click.get() else Click()

}