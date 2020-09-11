package me.mattstudios.triumphchat.config.bean.objects

/**
 * @author Matt
 */
data class Component(
        var text: String = "",
        var hover: List<String> = emptyList(),
        var click: ClickAction = ClickAction()
) {

    fun formatComponent(): String {
        if (text.isEmpty()) return text

        val joinedHover = hover.joinToString("\n")
        if (joinedHover.isEmpty() && (click.type == null || click.value == null)) return text

        val formattedHover = buildString {
            append("hover: ")
            append(joinedHover)
        }

        val formattedClick = click.getFormatted()

        return buildString {
            append("[")
            append(text)
            append("](")
            if (joinedHover.isNotEmpty()) append(formattedHover)
            if (joinedHover.isNotEmpty() && formattedClick != null) append("|")
            if (formattedClick != null) append(formattedClick)
            append(")")
        }
    }

}