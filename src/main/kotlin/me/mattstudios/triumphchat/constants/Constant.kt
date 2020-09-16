package me.mattstudios.triumphchat.constants

import me.mattstudios.triumphchat.config.bean.ChatFormat
import me.mattstudios.triumphchat.config.bean.objects.ClickAction
import me.mattstudios.triumphchat.config.bean.objects.Component

object Constant {

    val DEFAULT_FORMAT = ChatFormat(
        1, mapOf(
            "prefix" to Component("%vault_prefix% ", click = ClickAction("RUN_COMMAND", "ranks")),
            "name" to Component(
                "&f%player_name%",
                listOf("Click to send a message"),
                ClickAction("SUGGEST_COMMAND", "/msg %player_name% ")
            ),
            "message" to Component("&8> &a%message%", listOf("Sent @ %server_time ....%"))
        )
    )

}