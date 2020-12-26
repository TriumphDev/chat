package me.mattstudios.triumphchat.config.bean.objects.elements

import net.kyori.adventure.text.event.ClickEvent

data class ClickData(
    var type: String? = null,
    var value: String? = null
) {
    val isEmpty = type == null || value == null
    val action = selectAction()

    // Adds `/` to commands and makes it not nullable
    val finalValue = when {
        value?.startsWith('/') == false && action == ClickEvent.Action.RUN_COMMAND -> "/$value"
        else -> if (value == null) "" else "$value"
    }

    /**
     * Selects the correct click action
     */
    private fun selectAction() = when (type?.toUpperCase()) {
        "SUGGEST_COMMAND" -> ClickEvent.Action.SUGGEST_COMMAND
        "COPY_TO_CLIPBOARD" -> ClickEvent.Action.COPY_TO_CLIPBOARD
        else -> ClickEvent.Action.RUN_COMMAND
    }

}