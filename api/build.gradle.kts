triumph {
    msgs(mapOf("version" to "2.2.4-SNAPSHOT", "type" to "adventure", "scope" to "api"))
    platform(mapOf("version" to "4.0.0-SNAPSHOT", "scope" to "api"))
}

dependencies {
    // Kotlin
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")

    //api("net.kyori:text-serializer-plain:4.0.0-SNAPSHOT")
}