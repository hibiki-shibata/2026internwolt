plugins {
    id("org.jetbrains.kotlin.jvm") version "2.3.0"
    id("io.ktor.plugin") version "3.3.3"
    kotlin("plugin.serialization") version "2.3.0"
    application
}

repositories {
    mavenCentral()
}

val ktor_version = "3.3.3"
val kotlin_version = "2.3.0"

dependencies {
    // Testing
    testImplementation(libs.junit.jupiter.engine)
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    testImplementation("org.jetbrains.kotlin:kotlin-test:$kotlin_version")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.10.2")
    
    // Ktor Server
    implementation("io.ktor:ktor-server-core-jvm:$ktor_version")
    implementation("io.ktor:ktor-server-netty:$ktor_version")
    implementation("io.ktor:ktor-server-config-yaml:$ktor_version")
    implementation("ch.qos.logback:logback-classic:1.4.11")
    // Ktor Client
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")    
    // Client Serialization
    implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
    // Server Serialization
    implementation("io.ktor:ktor-server-content-negotiation:$ktor_version")
    // JSON Serialization
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.9.0")
    // Error Handling
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")
    // CORS
    implementation("io.ktor:ktor-server-cors:$ktor_version")
    // Logging
    implementation("io.ktor:ktor-server-call-logging:$ktor_version")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    // mainClass = "org.dopc.AppKt"
    mainClass= "io.ktor.server.netty.EngineMain"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

//Doc: https://ktor.io/docs/server-fatjar.html#build
ktor {
    fatJar {
        archiveFileName.set("2026woltinternExecutable.jar")
    }
}