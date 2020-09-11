package me.mattstudios.triumphchat.config.bean.objects

/**
 * @author Matt
 */
data class Component(
        var text: String = "",
        var hover: List<String> = emptyList(),
        var click: ClickAction = ClickAction()
) {

    fun formatComponent(): String {

        println(text)
        println(hover)
        println(click)

        return ""
    }

}