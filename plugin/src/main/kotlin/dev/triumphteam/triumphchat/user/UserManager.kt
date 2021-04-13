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

package dev.triumphteam.triumphchat.user

import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.api.ChatUser
import org.bukkit.entity.Player
import java.util.UUID

class UserManager(private val plugin: TriumphChat) {

    private val users = mutableMapOf<UUID, ChatUser>()

    init {
        // load all players
    }

    fun getUser(uuid: UUID): ChatUser {
        return users[uuid] ?: addUser(uuid)
    }

    fun getUser(player: Player) = getUser(player.uniqueId)

    private fun addUser(uuid: UUID): ChatUser {
        return PlayerUser(plugin, uuid).also {
            users[uuid] = it
        }
    }

}