import com.github.gradle.node.npm.task.NpxTask

val kotlin_version: String by project
val logback_version: String by project
val kotlinx_html_version: String by project

plugins {
    kotlin("jvm") version "2.0.0"
    id("io.ktor.plugin") version "2.3.12"

    /**
     * The node-gradle plugin enables running `npm` scripts from Gradle builds.
     *
     * This plugin is used to generate the tailwind css style from
     * Kotlin source.
     */
    id("com.github.node-gradle.node") version "7.0.2"
}

group = "example.com"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

kotlin {
    jvmToolchain(20)
}

repositories {
    mavenCentral()
}

/**
 * "tailwind" task runs `npx tailwindcss -o src/main/resources/tailwind/input.css`
 * command when the project gets build.
 *
 * The task type is provided by the node-gradle plugin.
 */
tasks.register<NpxTask>("tailwind") {
    command = "tailwindcss"
    args = listOf("-o", "static/styles.css")

    /**
     * The optional 'inputs' and 'outputs' configs help Gradle to optimize the build.
     *
     * E.g. if any of the inputs hasn't changed since last build, Gradle will
     * mark the task as "UP-TO-DATE" and skip it.
     *
     * These configs are not [NpxTask] specific, but rather generally available for all Gradle tasks.
     * See https://tomgregory.com/gradle/gradle-task-inputs-and-outputs/
      */
    inputs.dir("src")
    inputs.files("tailwind.config.js")
    outputs.files("static/styles.css")
}

/**
 * "classes" task depends on the custom tailwind task.
 */
tasks.named("classes") {
    dependsOn("tailwind")
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-html-builder-jvm")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinx_html_version")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    implementation("io.ktor:ktor-server-config-yaml")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
}
