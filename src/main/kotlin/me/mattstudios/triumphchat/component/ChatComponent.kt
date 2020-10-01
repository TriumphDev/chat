package me.mattstudios.triumphchat.component


import me.mattstudios.mfmsg.adventure.AdventureSerializer
import me.mattstudios.mfmsg.base.internal.components.MessageNode
import me.mattstudios.triumphchat.TriumphChat
import net.kyori.adventure.platform.bukkit.BukkitAudiences
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

/**
 * @author Matt
 */
class ChatComponent(private val parts: List<MessageNode>) {

    private val audience = BukkitAudiences.create(JavaPlugin.getPlugin(TriumphChat::class.java))

    fun sendMessage(player: Player) {
        audience.audience(player).sendMessage(AdventureSerializer.toComponent(parts))
        //NmsMessage.sendMessage(player, NodeSerializer.toJson(parts))
    }

}