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

package dev.triumphteam.triumphchat

import dev.triumphteam.core.TriumphPlugin
import dev.triumphteam.core.locale.Language
import dev.triumphteam.triumphchat.channel.ChannelManager
import dev.triumphteam.triumphchat.commands.MessageCommand
import dev.triumphteam.triumphchat.commands.ReloadCommand
import dev.triumphteam.triumphchat.commands.ReplyCommand
import dev.triumphteam.triumphchat.config.FormatsConfig
import dev.triumphteam.triumphchat.config.MainConfig
import dev.triumphteam.triumphchat.init.StartupMessage
import dev.triumphteam.triumphchat.init.checks.Dependencies
import dev.triumphteam.triumphchat.init.checks.ValidFormats
import dev.triumphteam.triumphchat.init.command.Arguments
import dev.triumphteam.triumphchat.init.command.CommandMessages
import dev.triumphteam.triumphchat.init.command.Completions
import dev.triumphteam.triumphchat.listeners.ChatListener
import dev.triumphteam.triumphchat.listeners.PlayerListener
import dev.triumphteam.triumphchat.locale.Message
import dev.triumphteam.triumphchat.managers.UserManager
import me.mattstudios.annotations.BukkitPlugin

/**
 * Main implementation of the plugin
 */
@BukkitPlugin
class TriumphChat : TriumphPlugin() {

    val userManager = UserManager(this)
    val channelManager = ChannelManager(this)

    override fun enable() {
        config(MainConfig)
        config(FormatsConfig)
        locale(Message, Language.ENGLISH)

        initialize(StartupMessage)

        if (!check(Dependencies)) return
        check(ValidFormats)

        commands {
            initialize(Arguments)
            initialize(Completions)
            initialize(CommandMessages)

            register(
                MessageCommand(plugin),
                ReplyCommand(plugin),
                ReloadCommand(plugin),
            )
        }

        listeners {
            register(
                ChatListener(plugin),
                PlayerListener(userManager),
            )
        }
    }

}