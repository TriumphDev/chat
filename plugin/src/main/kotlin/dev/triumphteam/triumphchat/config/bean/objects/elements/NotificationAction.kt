package dev.triumphteam.triumphchat.config.bean.objects.elements

// Regex for the action syntax `[action] Message`
private val ACTION_REGEX = "\\[(?<type>\\w+)](?<value>.+)".toRegex()

/**
 * Notification action holds the data of the action, like type and message value
 */
internal data class NotificationAction(
    val actionType: ActionType,
    val value: String
) {
    companion object {
        /**
         * Simple function to create an instance of the action based on the string value
         */
        fun from(string: String): NotificationAction? {
            val (type, value) = ACTION_REGEX.matchEntire(string)?.destructured ?: return null
            val action = ActionType.values().find { it.name == type.toUpperCase() } ?: return null
            return NotificationAction(action, value)
        }
    }
}

internal enum class ActionType {

    ACTIONBAR,
    TITLE,
    SUBTITLE

}