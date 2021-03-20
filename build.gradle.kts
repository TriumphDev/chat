import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.31"
    id("me.mattstudios.triumph") version "0.1.8"
}

allprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        plugin("me.mattstudios.triumph")
    }

    group = "me.mattstudios"
    version = "1.0.0-SNAPSHOT"

    repositories {
        mavenLocal()
        mavenCentral()

        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }

    triumph {
        spigot("1.16.4")
        papi("2.10.9")
    }

    tasks {
        withType<JavaCompile> {
            options.compilerArgs.add("-parameters")
            options.encoding = "UTF-8"
        }

        withType<KotlinCompile> {
            kotlinOptions {
                jvmTarget = "1.8"
                javaParameters = true
            }
        }
    }

}