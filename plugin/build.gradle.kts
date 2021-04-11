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
        // TODO add unit testing
    }

    withType<ShadowJar> {

        // Relocations
        mapOf(
            "dev.triumphteam.core" to "core",
            "me.mattstudios.mf" to "lib.cmds",
            "me.mattstudios.msg" to "lib.msg",
            "me.mattstudios.config" to "lib.config",

            "kotlin" to "kotlin",
        ).forEach {
            relocate(it.key, "dev.triumphteam.triumphchat.${it.value}")
        }

        // TODO decide if should or not relocate adventure

        archiveFileName.set("TriumphChat-${project.version}.jar")

        // Testing purposes
        destinationDirectory.set(File("C:/Users/xpsyk/Desktop/servers/paper-1.16/plugins"))
    }
}

bukkit {
    name = "TriumphChat"
    softdepend = mutableListOf("PlaceholderAPI")
    apiVersion = "1.13"
}