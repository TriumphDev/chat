/*
 * MIT License
 *
 * Copyright (c) 2021 Mateus Moreira
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.triumphteam.triumphchat.data

import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.api.Channel
import dev.triumphteam.triumphchat.api.ChatUser
import dev.triumphteam.triumphchat.api.Message
import dev.triumphteam.triumphchat.func.AUDIENCES
import dev.triumphteam.triumphchat.func.sendMessage
import dev.triumphteam.triumphchat.func.toPlayer
import dev.triumphteam.triumphchat.permissions.ChatPermission
import me.mattstudios.msg.base.internal.Format
import net.kyori.adventure.audience.Audience
import net.kyori.adventure.identity.Identity
import org.bukkit.Bukkit
import java.util.UUID

data class PlayerUser(
    private val plugin: TriumphChat,
    override val uuid: UUID,
    override var replyTarget: UUID? = null
) : ChatUser {

    override var channel: Channel
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun sendMessage(message: Message) {
        uuid.toPlayer()?.sendMessage(Identity.identity(message.author.uuid), message.component)
    }

    override fun getChatFormats(): Set<Format> {
        val player = Bukkit.getPlayer(uuid) ?: return Format.NONE
        return ChatPermission.formats.filter { player.hasPermission(it.key) }.values.toSet()
    }

    override fun audience(): Audience {
        // This is temporary
        return AUDIENCES.player(uuid)
    }

    override fun identity() = Identity.identity(uuid)

}