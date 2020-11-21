package me.mattstudios.triumphchat.chat

import me.mattstudios.core.configuration.Config
import me.mattstudios.mfmsg.base.internal.color.FlatColor
import me.mattstudios.mfmsg.base.internal.color.MessageColor
import me.mattstudios.mfmsg.base.internal.components.MessageNode
import me.mattstudios.mfmsg.base.internal.extensions.ReplaceableExtension
import me.mattstudios.triumphchat.component.ChatComponentBuilder
import me.mattstudios.triumphchat.config.Settings
import me.mattstudios.triumphchat.config.bean.objects.Click
import me.mattstudios.triumphchat.func.parsePAPI
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player

class ChatMessage(
    private val player: Player,
    private val rawMessage: String,
    private val recipients: MutableSet<Player>,
    private val config: Config
) : TriumphMessage {

    private var defaultColor: MessageColor = FlatColor("white")
    override val component = getMessage()

    fun sendMessage() {
        recipients.forEach {
            //component.sendMessage(it)
        }
    }

    private fun getMessage(): Component {
        val formats = config[Settings.FORMATS]
        /*val format = formats
                .filter { player.hasPermission("${Permission.FORMAT.permission}${it.key}") }
                .maxByOrNull { it.value.priority }?.value ?: formats["default"] ?: DEFAULT_FORMAT

        val components = format.components*/

        val chatComponent = ChatComponentBuilder()

        /*components.forEach { (_, component) ->
            println(component)
            //if (component.isMessage) handleMessage(component, chatComponent)
            //else chatComponent.append(component, player)
        }*/

        return chatComponent.build()
    }

    /*private fun handleMessage(baseMessageComponent: BaseComponent, chatComponent: ChatComponentBuilder) {
        val message = baseMessageComponent.text
        val matcher = MESSAGE_PATTERN.matcher(message)

        var rest = message
        var start = 0
        while (matcher.find()) {
            val before = message.substring(start, matcher.start())
            if (before.isNotEmpty()) append(chatComponent, before, baseMessageComponent.hover, baseMessageComponent.click)

            // TODO Add formats
            chatComponent.append(
                AdventureMessage.create(
                    MessageOptions.Builder(EnumSet.allOf(Format::class.java)).setReplaceableHandler(Test()).build()
                ).parseToNodes(rawMessage),
                baseMessageComponent.hover,
                baseMessageComponent.click,
                player
            )

            start = matcher.end()
            rest = message.substring(start)
        }

        if (rest.isNotEmpty()) append(chatComponent, rest, baseMessageComponent.hover, baseMessageComponent.click)

    }*/

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
            /*if (literal == "item") {
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
            }*/

            return null
        }

    }

}
