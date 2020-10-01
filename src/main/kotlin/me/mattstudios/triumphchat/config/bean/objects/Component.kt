package me.mattstudios.triumphchat.config.bean.objects

import me.mattstudios.triumphchat.events.MESSAGE_PLACEHOLDER

/**
 * @author Matt
 */
data class Component(
    var text: String = "",
    var hover: List<String> = emptyList(),
    var click: Click = Click()
) {

    val isMessage: Boolean
        get() = MESSAGE_PLACEHOLDER in text

}