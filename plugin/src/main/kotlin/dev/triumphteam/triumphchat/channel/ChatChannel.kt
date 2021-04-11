package dev.triumphteam.triumphchat.channel

import dev.triumphteam.triumphchat.api.Channel
import net.kyori.adventure.audience.Audience

class ChatChannel : Channel {

    override val prefix = ""

    override fun audiences(): MutableIterable<Audience> {
        return mutableListOf()
    }

}