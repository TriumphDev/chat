import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://jitpack.io")
    maven("https://repo.mattstudios.me/artifactory/public/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots/")
}

triumph {
    core("1.2.3")
}

dependencies {
    implementation(project(":api"))

    compileOnly("com.github.MilkBowl:VaultAPI:1.7") {
        isTransitive = false
    }

    // Testing
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.assertj:assertj-core:3.11.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.6.0")

}


tasks {
    test {

    }

    withType<ShadowJar> {
        relocate("dev.triumphteam.core", "me.mattstudios.triumphchat.core")
        relocate("me.mattstudios.mf", "me.mattstudios.triumphchat.mf")
        relocate("me.mattstudios.mfgui", "me.mattstudios.triumphchat.mfgui")
        relocate("me.mattstudios.msg", "me.mattstudios.triumphchat.msg")

        relocate("ch.jalu.configme", "me.mattstudios.triumphchat.configme")
        //relocate("net.kyori", "me.mattstudios.triumphchat.kyori")

        relocate("kotlin", "me.mattstudios.triumphchat.kotlin")

        archiveFileName.set("TriumphChat-${project.version}.jar")

        destinationDirectory.set(File("C:/Users/xpsyk/Desktop/servers/paper-1.16/plugins"))
    }
}

bukkit {
    name = "TriumphChat"
    softdepend = mutableListOf("PlaceholderAPI")
    apiVersion = "1.13"
}