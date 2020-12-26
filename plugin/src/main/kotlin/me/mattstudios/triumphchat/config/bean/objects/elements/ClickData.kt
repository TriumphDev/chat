package me.mattstudios.triumphchat.config.bean.objects.elements

import me.mattstudios.msg.base.internal.Format

data class ClickData(
    var type: String? = null,
    var value: String? = null
) {
    val isEmpty = type == null || value == null
    val action = selectAction()

    // Adds `/` to commands and makes it not nullable
    val finalValue = when {
        value?.startsWith('/') == false && action == Format.ACTION_COMMAND -> "/$value"
        else -> if (value == null) "" else "$value"
    }

    /**
     * Selects the correct click action
     */
    private fun selectAction() = when (type?.toUpperCase()) {
        "SUGGEST_COMMAND" -> Format.ACTION_SUGGEST
        "COPY_TO_CLIPBOARD" -> Format.ACTION_CLIPBOARD
        else -> Format.ACTION_COMMAND
    }

}