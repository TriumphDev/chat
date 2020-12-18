package me.mattstudios.triumphchat.config.bean.objects

import me.mattstudios.config.annotations.Name
import me.mattstudios.triumphchat.config.bean.objects.elements.ClickData
import java.util.Optional

data class BaseDisplay(
    override var text: String = "",
    @Name("hover") var hoverData: Optional<List<String>> = Optional.empty<List<String>>(),
    @Name("click") var clickData: Optional<ClickData> = Optional.empty()
) : FormatDisplay {

    override val hover: List<String> = hoverData.orElseGet { emptyList() }
    override val click: ClickData = clickData.orElseGet { ClickData() }

}
