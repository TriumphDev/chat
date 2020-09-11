package me.mattstudios.triumphchat.config.bean.objects

/**
 * @author Matt
 */
data class ClickAction(
        var type: String? = null,
        var value: String? = null
) {

    fun getFormatted(): String? {
        if (type == null || value == null) return null

        val formatType = when (type?.toUpperCase()) {
            "SUGGEST_COMMAND" -> "suggest"
            "RUN_COMMAND" -> "command"
            "COPY_TO_CLIPBOARD" -> "clipboard"
            else -> null
        } ?: return null

        return buildString {
            append(formatType)
            append(": ")
            append(value)
        }
    }

}