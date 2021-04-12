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

package dev.triumphteam.triumphchat.commands

import dev.triumphteam.triumphchat.api.ChatUser
import dev.triumphteam.triumphchat.config.MainConfig
import dev.triumphteam.triumphchat.func.sendPrivateMessage
import dev.triumphteam.triumphchat.func.sendTo
import dev.triumphteam.triumphchat.locale.Message
import dev.triumphteam.triumphchat.permissions.Permissions
import me.mattstudios.mf.annotations.Alias
import me.mattstudios.mf.annotations.Command
import me.mattstudios.mf.annotations.Completion
import me.mattstudios.mf.annotations.Default
import me.mattstudios.mf.annotations.Permission
import me.mattstudios.mf.base.CommandBase
import org.bukkit.entity.Player

@Command("msg")
@Alias("m")
class MessageCommand(private val plugin: dev.triumphteam.triumphchat.TriumphChat) : CommandBase() {

    private val userManager = plugin.userManager
    private val config = plugin.config<MainConfig>()
    private val locale = plugin.locale

    @Default
    @Permission(Permissions.COMMAND_MESSAGE)
    fun sendMessage(
        sender: Player,
        @Completion("#players") recipient: ChatUser?,
        @Completion("#empty") args: Array<String>
    ) {
        
        if (recipient == null) {
            locale[Message.MESSAGE_RECIPIENT_NO_EXIST].sendTo(sender)
            return
        }

        recipient.sendPrivateMessage(userManager.getUser(sender), args.joinToString(" "), plugin)
    }

}