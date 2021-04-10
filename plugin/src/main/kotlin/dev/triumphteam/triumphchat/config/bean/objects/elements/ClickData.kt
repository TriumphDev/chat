package dev.triumphteam.triumphchat.config.bean.objects.elements

import dev.triumphteam.triumphchat.api.ChatUser
import dev.triumphteam.triumphchat.func.parsePAPI
import me.mattstudios.msg.base.internal.Format
import me.mattstudios.msg.base.internal.action.ClickMessageAction

/**
 * Holds the click data, also makes sure the values are correct, like adding `/` to commands
 */
data class ClickData(
    var type: String? = null,
    var value: String? = null
) {

    val isNotEmpty = type != null && value != null
    private val action = selectAction()

    // Adds `/` to commands and makes it not nullable
    private val finalValue = when {
        value?.startsWith('/') == false && action == Format.ACTION_COMMAND -> "/$value"
        else -> if (value == null) "" else "$value"
    }

    fun toClick(sender: ChatUser? = null, recipient: ChatUser? = null): ClickMessageAction {
        return ClickMessageAction(action, finalValue.parsePAPI(sender, recipient))
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