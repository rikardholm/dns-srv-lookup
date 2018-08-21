import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.2.51"
}

val compileTestKotlin: KotlinCompile by tasks

compileTestKotlin.kotlinOptions.jvmTarget = "1.8"
compileTestKotlin.kotlinOptions.jvmTarget = "1.8"

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.jvmTarget = "1.8"

val compileJava: JavaCompile by tasks

compileJava.targetCompatibility = "1.8"
compileJava.sourceCompatibility = "1.8"

val compileTestJava: JavaCompile by tasks

compileTestJava.targetCompatibility = "1.8"
compileTestJava.sourceCompatibility = "1.8"


dependencies {
    testCompile("org.jetbrains.spek:spek-api:1.1.5")
    testRuntime("org.jetbrains.spek:spek-junit-platform-engine:1.1.5")
    testCompile(kotlin("stdlib-jdk8"))
    testCompile(kotlin("reflect"))

    testCompile("com.github.golovnin:embedded-consul:1.2.1.0")
    testCompile("com.ecwid.consul:consul-api:1.4.0")

    testRuntime("org.slf4j:slf4j-simple:1.7.25")
}

repositories {
    jcenter()
}

