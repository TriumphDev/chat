package dev.triumphteam.triumphchat.config.bean.holders

import dev.triumphteam.triumphchat.config.bean.objects.FormatDisplay
import dev.triumphteam.triumphchat.func.DEFAULT_FORMAT_MAP
import dev.triumphteam.triumphchat.func.MESSAGE_PLACEHOLDER
import me.mattstudios.config.annotations.Comment

/**
 * Holds the chat formats with a priority and it's components
 */
data class FormatsHolder(
    @Comment(
        "You can list as many formats as you want.",
        "Formats have specific components, the components can be anything you want and will be displayed in the order they are declared.",
        "You can use any placeholder from PlaceholderAPI.",
        "Make sure the message placeholder ($MESSAGE_PLACEHOLDER) is present in one of the components!",
        // TODO priority comment
        // TODO Add link to the wiki explaining this better
        "The priority is..."
    )
    var formats: MutableMap<String, MutableMap<String, FormatDisplay>> = DEFAULT_FORMAT_MAP
)