package me.mattstudios.triumphchat.events

import me.mattstudios.mfmsg.adventure.AdventureMessage
import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.objects.Click
import me.mattstudios.triumphchat.config.bean.objects.Component
import java.util.regex.Pattern

val MESSAGE_PLACEHOLDER = "%message%"

val DEFAULT_MESSAGE = AdventureMessage.create()

val DEFAULT_FORMAT = ChatFormat(
    1, mapOf(
        "prefix" to Component("%vault_prefix% ", click = Click("RUN_COMMAND", "ranks")),
        "name" to Component(
            "&f%player_name%",
            listOf("Click to send a message"),
            Click("SUGGEST_COMMAND", "/msg %player_name% ")
        ),
        "message" to Component("&8> %message%", listOf("Sent @ %server_time%"))
    )
)

val MESSAGE_PATTERN: Pattern = Pattern.compile("(?<message>%message%)")
