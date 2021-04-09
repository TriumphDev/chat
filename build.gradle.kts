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
    }

    dependencies {
        // Testing
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testImplementation("org.assertj:assertj-core:3.11.1")
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
        testImplementation("org.junit.jupiter:junit-jupiter-engine:5.6.0")
    }

    triumph {
        spigot("1.16.5")
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