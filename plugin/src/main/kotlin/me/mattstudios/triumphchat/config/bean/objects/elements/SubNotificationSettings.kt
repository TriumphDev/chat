package me.mattstudios.triumphchat.config.bean.objects.elements

import me.mattstudios.config.annotations.Name
import java.util.Optional

/**
 * This is used for group specific notification settings
 */
data class SubNotificationSettings(
    var sound: Boolean = true,
    @Name("actions") var optionalActions: Optional<List<String>> = Optional.of(listOf("[actionbar] Test!"))
) {

    internal val actions = createActionsList()

    private fun createActionsList(): List<NotificationAction> {
        val actions = if (optionalActions.isPresent) optionalActions.get() else return emptyList()
        return actions.mapNotNull { NotificationAction.from(it) }
    }

}
