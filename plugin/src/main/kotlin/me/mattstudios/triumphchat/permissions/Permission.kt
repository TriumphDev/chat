package me.mattstudios.triumphchat.permissions

import me.mattstudios.triumphchat.func.MAIN_PERMISSION

enum class Permission(val permission: String) {

    FORMAT("${MAIN_PERMISSION}.chatformat");

    fun test() {
        val map = mutableMapOf<String, Int>()
        map.merge("", 0, Int::plus)
        if ("" in map)
        println()
    }

}