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

import dev.triumphteam.triumphchat.TriumphChat
import dev.triumphteam.triumphchat.config.Config
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

@Command("reply")
@Alias("r")
class ReplyCommand(private val plugin: TriumphChat) : CommandBase() {

    private val userManager = plugin.userManager
    private val config = plugin.config<Config>()

    @Default
    @Permission(Permissions.COMMAND_REPLY)
    fun sendReply(
        sender: Player,
        @Completion("#empty") args: Array<String>
    ) {

        if (args.isEmpty()) {
            sendMessage("cmd.wrong.usage", sender)
            return
        }

        val author = userManager.getUser(sender)

        val replyTarget = author.replyTarget ?: run {
            plugin.locale[Message.REPLY_NO_REPLY_TARGET].sendTo(sender)
            return
        }

        userManager.getUser(replyTarget).sendPrivateMessage(author, args.joinToString(" "), plugin)
    }

}