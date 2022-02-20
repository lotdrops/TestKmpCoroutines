package plugins

import org.gradle.kotlin.dsl.apply

plugins {
    id("org.jlleitschuh.gradle.ktlint-idea")
}

subprojects {
    apply(plugin = "org.jlleitschuh.gradle.ktlint")

    ktlint {
        // USE WITH: ./gradlew --continue ktlintCheck
        version.set("0.40.0")
        android.set(true)
        ignoreFailures.set(false)
    }
}
