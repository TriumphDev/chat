package me.mattstudios.triumphchat.config.bean.objects.elements

import me.mattstudios.msg.base.internal.Format
import me.mattstudios.msg.base.internal.action.ClickMessageAction
import me.mattstudios.triumphchat.api.ChatPlayer
import me.mattstudios.triumphchat.func.parsePAPI
import net.kyori.adventure.text.event.ClickEvent

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
    val finalValue = when {
        value?.startsWith('/') == false && action == Format.ACTION_COMMAND -> "/$value"
        else -> if (value == null) "" else "$value"
    }

    fun createAdventureClick(sender: ChatPlayer? = null, recipient: ChatPlayer? = null): ClickEvent {
        return ClickEvent.clickEvent(formatToAdventure(), finalValue.parsePAPI(sender, recipient))
    }

    fun createClick(sender: ChatPlayer? = null, recipient: ChatPlayer? = null): ClickMessageAction {
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

    private fun formatToAdventure() = when (action) {
        Format.ACTION_SUGGEST -> ClickEvent.Action.SUGGEST_COMMAND
        Format.ACTION_CLIPBOARD -> ClickEvent.Action.COPY_TO_CLIPBOARD
        else -> ClickEvent.Action.RUN_COMMAND
    }

}