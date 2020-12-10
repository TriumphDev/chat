package me.mattstudios.triumphchat.extensions.parser

import me.mattstudios.msg.commonmark.internal.inline.ParsedInline
import me.mattstudios.msg.commonmark.internal.inline.Scanner
import me.mattstudios.msg.commonmark.internal.inline.triumph.TriggerProcessor
import me.mattstudios.triumphchat.extensions.parser.nodes.PingNode

class PingParser : TriggerProcessor {

    override fun getTriggerCharacter() = '@'

    override fun parse(scanner: Scanner): ParsedInline? {
        // Skips the `@`
        scanner.next()

        // Gets the start position
        val start = scanner.position()

        // The character count
        var characters = 0
        while (scanner.hasNext()) {
            scanner.next()

            val char = scanner.peek()

            // Checks if it's not a letter, space, or underline
            if (char == ' ' || (!Character.isLetter(char) && char != '_')) {
                val literal = scanner.textBetween(start, scanner.position()).toString()
                return ParsedInline.of(PingNode(literal), scanner.position())
            }

            if (characters > 16) break

            characters++
        }

        // No ping found
        return ParsedInline.none()
    }

}