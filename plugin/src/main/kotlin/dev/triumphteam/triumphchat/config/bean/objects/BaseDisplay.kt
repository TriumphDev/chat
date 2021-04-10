package dev.triumphteam.triumphchat.config.bean.objects

import dev.triumphteam.triumphchat.config.bean.objects.elements.ClickData
import me.mattstudios.config.annotations.Name
import java.util.Optional

/**
 * Base implementation for the [FormatDisplay] that doesn't hold format data
 */
data class BaseDisplay(
    override var text: String = "",
    @Name("hover") var hoverData: Optional<List<String>> = Optional.empty<List<String>>(),
    @Name("click") var clickData: Optional<ClickData> = Optional.empty()
) : FormatDisplay {

    override val hover: List<String> = hoverData.orElseGet { emptyList() }
    override val click: ClickData = clickData.orElseGet { ClickData() }

}
