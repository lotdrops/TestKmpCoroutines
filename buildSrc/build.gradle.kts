plugins {
    `kotlin-dsl`
}

repositories {
    mavenCentral()
    google()
    jcenter()
    maven("https://plugins.gradle.org/m2/")
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}

dependencies {
    implementation("com.android.tools.build:gradle:7.0.4")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.16.0-RC3")
    implementation("org.jlleitschuh.gradle:ktlint-gradle:10.0.0")
    implementation("com.github.ben-manes:gradle-versions-plugin:0.36.0")
    implementation(gradleApi())
    implementation(localGroovy())
}