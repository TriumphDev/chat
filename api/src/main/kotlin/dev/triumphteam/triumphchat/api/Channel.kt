package dev.triumphteam.triumphchat.api

import net.kyori.adventure.audience.ForwardingAudience

interface Channel : ForwardingAudience {

    val prefix: String

}