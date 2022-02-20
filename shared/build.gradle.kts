import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.kotlin.native.cocoapods")
    id("com.squareup.sqldelight")
    id("com.rickclephas.kmp.nativecoroutines")
}

// CocoaPods requires the podspec to have a version.
version = "1.0"

sqldelight {
    database("AppDatabase") {
        packageName = "sqldelight.com.example.kmmbase.shared"
    }
}

sharedCommonAndroidConfig()

kotlin {
    val xcf = XCFramework("Shared")
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { target ->
        target.binaries.framework {
            baseName = "Shared"
            xcf.add(this)
        }
    }

    android()

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Some description for a Kotlin/Native module"
        homepage = "Link to a Kotlin/Native module homepage"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Coroutines.common)
                implementation(Dependencies.SQLDelight.runtime)

                api(project(Modules.Shared.Kt))
                api(project(Modules.Shared.Core))
                api(project(Modules.Shared.Example))
                implementation(project(Modules.Shared.Commons))
            }
        }
        val commonTest by getting
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Android.lifecycleSavedState)
                implementation(Dependencies.Compose.runtimeSaveable)
                implementation(Dependencies.Android.material)
                implementation(Dependencies.SQLDelight.androidDriver)
                implementation(Dependencies.Koin.android)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation(Dependencies.Test.junit)
            }
        }

        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Dependencies.SQLDelight.nativeDriver)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}
