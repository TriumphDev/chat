package me.mattstudios.triumphchat.constants


enum class Permission(val permission: String) {

    FORMAT("${Permission.PERMISSION_CONSTANT}.format.");


    companion object {
        private const val PERMISSION_CONSTANT = "triumphchat."
    }

}