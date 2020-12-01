import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

plugins {
    kotlin("jvm") version "1.4.20"
    application
}

group = "com.josepedrodias.aoc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit"))
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

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "Day01Kt"
}