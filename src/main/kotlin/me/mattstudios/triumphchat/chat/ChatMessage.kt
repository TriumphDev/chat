package me.mattstudios.triumphchat.chat

import me.mattstudios.core.configuration.Config
import me.mattstudios.mfmsg.adventure.AdventureMessage
import me.mattstudios.mfmsg.base.MessageOptions
import me.mattstudios.mfmsg.base.internal.Format
import me.mattstudios.mfmsg.base.internal.action.HoverMessageAction
import me.mattstudios.mfmsg.base.internal.action.content.HoverContent
import me.mattstudios.mfmsg.base.internal.color.FlatColor
import me.mattstudios.mfmsg.base.internal.color.MessageColor
import me.mattstudios.mfmsg.base.internal.components.MessageNode
import me.mattstudios.mfmsg.base.internal.components.ReplaceableNode
import me.mattstudios.mfmsg.base.internal.components.TextNode
import me.mattstudios.mfmsg.base.internal.extensions.ReplaceableExtension
import me.mattstudios.triumphchat.component.ChatComponent
import me.mattstudios.triumphchat.component.ChatComponentBuilder
import me.mattstudios.triumphchat.config.Settings
import me.mattstudios.triumphchat.config.bean.objects.Click
import me.mattstudios.triumphchat.config.bean.objects.Component
import me.mattstudios.triumphchat.constants.Permission
import me.mattstudios.triumphchat.events.DEFAULT_FORMAT
import me.mattstudios.triumphchat.events.MESSAGE_PATTERN
import me.mattstudios.triumphchat.func.parsePAPI
import org.bukkit.ChatColor
import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack
import org.bukkit.entity.Player
import java.util.*


/**
 * @author Matt
 */
class ChatMessage(
    private val player: Player,
    private val rawMessage: String,
    private val recipients: MutableSet<Player>,
    private val config: Config
) {

    private var defaultColor: MessageColor = FlatColor("white")
    private val component = getMessage()

    fun sendMessage() {
        recipients.forEach {
            component.sendMessage(it)
        }
    }

    private fun getMessage(): ChatComponent {
        val formats = config[Settings.FORMATS]
        val format = formats
                .filter { player.hasPermission("${Permission.FORMAT.permission}${it.key}") }
                .maxByOrNull { it.value.priority }?.value ?: formats["default"] ?: DEFAULT_FORMAT

        val components = format.components

        val chatComponent = ChatComponentBuilder()

        components.forEach { (_, component) ->
            if ("%message%" in component.text) handleMessage(component, chatComponent)
            else chatComponent.append(component, player)
        }

        return chatComponent.build()
    }

    private fun handleMessage(component: Component, chatComponent: ChatComponentBuilder) {
        val message = component.text
        val matcher = MESSAGE_PATTERN.matcher(message)

        var rest = message
        var start = 0
        while (matcher.find()) {
            val before = message.substring(start, matcher.start())
            if (before.isNotEmpty()) append(chatComponent, before, component.hover, component.click)

            chatComponent.append(
                AdventureMessage.create(
                    MessageOptions.Builder(EnumSet.allOf(Format::class.java)).setReplaceableHandler(Test()).build()
                ).parseToNodes(rawMessage),
                component.hover,
                component.click,
                player
            )

            start = matcher.end()
            rest = message.substring(start)
        }

        if (rest.isNotEmpty()) append(chatComponent, rest, component.hover, component.click)

    }

    private fun append(
        chatComponent: ChatComponentBuilder,
        text: String,
        hover: List<String>,
        click: Click,
        shouldColor: Boolean = false
    ) {
        if (shouldColor) chatComponent.append(text, defaultColor = defaultColor)
        else chatComponent.append(text)
        chatComponent.addHover(hover.joinToString("\\n") { it.parsePAPI(player) })
        chatComponent.addClick(click, player)

    }

    inner class Test : ReplaceableExtension('{', '}', 1) {

        override fun getNode(literal: String): MessageNode? {
            if (literal == "item") {
                val item = player.inventory.itemInMainHand
                val nmsItemStack = CraftItemStack.asNMSCopy(item)
                //println(LocaleLanguage.a(nmsItemStack.name))

                val test = ReplaceableNode()
                val itemMeta = item.itemMeta

                val itemName = if (itemMeta != null) {
                    buildString {
                        append("[")
                        if (itemMeta.hasEnchants()) append(ChatColor.AQUA).append(ChatColor.ITALIC)

                        if (itemMeta.hasDisplayName()) {
                            append(itemMeta.displayName)
                        } else {
                            append(item.type.name.replace("_", " ").toLowerCase())
                        }
                        append(ChatColor.RESET)
                        append("]")
                    }
                } else {
                    buildString {
                        append("[")
                        append(item.type.name.replace("_", " ").toLowerCase())
                        append("]")
                    }
                }

                val node = TextNode(itemName)

                val nbt = nmsItemStack.tag.toString()
                println(nbt)
                node.actions = listOf(
                    HoverMessageAction(
                        HoverContent.showItem(
                            item.type.name.toLowerCase(),
                            1,
                            nbt
                        )
                    )
                )
                test.addNode(node)
                return test
            }

            return null
        }

    }

}
