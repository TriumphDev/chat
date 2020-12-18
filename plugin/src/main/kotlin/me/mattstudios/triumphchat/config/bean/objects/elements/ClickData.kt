package me.mattstudios.triumphchat.config.bean.objects.elements

data class ClickData(
    var type: String? = null,
    var value: String? = null
) {
    val isEmpty = type == null || value == null
}