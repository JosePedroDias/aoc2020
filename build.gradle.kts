import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import java.io.File

plugins {
    kotlin("jvm") version "1.4.20"
    application
}

group = "com.josepedrodias.aoc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    testImplementation(kotlin("test-junit"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
}

tasks.test {
    useJUnit()

    testLogging {
        testLogging {
            events = setOf(FAILED, PASSED, SKIPPED, STANDARD_OUT)
            exceptionFormat = TestExceptionFormat.FULL
            showExceptions = true
            showCauses = true
            showStackTraces = true
        }
        addTestListener(object : TestListener {
            override fun beforeTest(d: TestDescriptor?) = Unit
            override fun beforeSuite(d: TestDescriptor?) = Unit
            override fun afterTest(d: TestDescriptor, r: TestResult) = Unit
            override fun afterSuite(d: TestDescriptor, r: TestResult) {
                if (d.parent == null) {
                    println("\nTest result: ${r.resultType}")
                    println("Summary: ${r.testCount} tests: ${r.successfulTestCount} succeeded, ${r.failedTestCount} failed, ${r.skippedTestCount} skipped")
                }
            }
        })
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "AOCKt"
}

// https://github.com/gradle/kotlin-dsl-samples/tree/master/samples
open class AocTask : DefaultTask() {
    @set:Option(option = "day", description = "day to generate")
    @get:Input
    var day: String = ""

    @TaskAction
    fun generateFiles() {
        if (day.isEmpty()) {
            throw Exception("Please provide a day. ex: --day=99")
        }
        println("Generating day $day...")

        File("aoc/$day.md").createNewFile()
        File("aoc/$day.txt").createNewFile()

        File("src/main/kotlin/d$day").mkdir()
        val f = File("src/main/kotlin/d$day/main.kt")
        f.createNewFile()
        f.writeText("""package d$day

import java.io.File

internal fun parseInput(): Sequence<String> {
    return File("aoc/$day.txt").readLines().asSequence()
}

fun parseLines(lines:Sequence<String>) {
    lines.forEach {
    }
}

fun part1(): Int {
    return 0
}

fun part2():Int {
    return 0
}

fun main() {
    val m = parseLines( parseInput() )

    val answer1 = part1(m)
    println("R1: ${"$"}answer1\n")

    val answer2 = part2(m)
    println("R2: ${"$"}answer2\n")
}""")

        val t = File("src/test/kotlin/Day${day}Test.kt")
        t.createNewFile()
        t.writeText("""import org.junit.Test
import org.junit.Assert.assertEquals

import d$day.part1

class Day${day}Test {
    @Test
    fun sample01() {
        assertEquals(1, 1)
    }
}""")
    }
}

tasks.register<AocTask>("aoc")
