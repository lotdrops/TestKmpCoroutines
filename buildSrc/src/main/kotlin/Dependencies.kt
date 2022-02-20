object Versions {
    // Kotlin
    const val kotlin = "1.6.10"
    const val koin = "3.1.4"
    const val coroutines = "1.6.0"
    const val detekt = "1.16.0-RC3"

    // UI
    const val lifecycle = "2.4.0-alpha01"
    const val material = "1.3.0"
    const val compose = "1.1.0-rc01"
    const val compose_compiler = "1.1.0-rc02"
    const val activity_compose = "1.4.0"
    const val nav_compose = "2.4.0-beta02"
    const val accompanist = "0.22.0-rc"
    const val coil = "1.4.0"

    // Android Gradle Plugin
    const val androidGradle = "7.0.4"


    // Other
    const val sqldelight = "1.5.3"
    const val kmpNativeCoroutines = "0.11.3-new-mm"
    const val kermit = "1.0.0"
    const val settings = "0.8.1"

    const val junit = "4.13"
}

object GradlePlugins {
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradle}"
    const val sqldelightGradle = "com.squareup.sqldelight:gradle-plugin:${Versions.sqldelight}"
    const val kmpNativeCoroutinesGradle = "com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:${Versions.kmpNativeCoroutines}"
}

object Dependencies {

    object Coroutines {
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    }

    object Koin {
        const val core = "io.insert-koin:koin-core:${Versions.koin}"
        const val android = "io.insert-koin:koin-android:${Versions.koin}"
        const val compose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
        const val test = "io.insert-koin:koin-test:${Versions.koin}"
    }

    object Compose {
        const val compiler = "androidx.compose.compiler:compiler:${Versions.compose_compiler}"
        const val ui = "androidx.compose.ui:ui:${Versions.compose}"
        const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
        const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
        const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
        const val material = "androidx.compose.material:material:${Versions.compose}"
        const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:${Versions.compose}"
        const val runtimeSaveable = "androidx.compose.runtime:runtime-saveable:${Versions.compose}"
        const val activity = "androidx.activity:activity-compose:${Versions.activity_compose}"
        const val navigation = "androidx.navigation:navigation-compose:${Versions.nav_compose}"
        const val accompanist = "com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}"
        const val coil = "io.coil-kt:coil-compose:${Versions.coil}"
    }

    object Android {
        const val lifecycleSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
        const val lifecycle = "androidx.lifecycle:lifecycle-common:${Versions.lifecycle}"
        const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object SQLDelight {
        const val plugin = "com.squareup.sqldelight:gradle-plugin:${Versions.sqldelight}"
        const val runtime = "com.squareup.sqldelight:runtime:${Versions.sqldelight}"
        const val androidDriver = "com.squareup.sqldelight:android-driver:${Versions.sqldelight}"
        const val nativeDriver = "com.squareup.sqldelight:native-driver:${Versions.sqldelight}"
    }

    object Other {
        const val kermit = "co.touchlab:kermit:${Versions.kermit}"
        const val settings = "com.russhwolf:multiplatform-settings:${Versions.settings}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
    }
}

object DependencyGroups {
    val compose = listOf(
        Dependencies.Compose.compiler,
        Dependencies.Compose.ui,
        Dependencies.Compose.uiGraphics,
        Dependencies.Compose.uiTooling,
        Dependencies.Compose.foundationLayout,
        Dependencies.Compose.material,
        Dependencies.Compose.runtimeLiveData,
        Dependencies.Compose.activity,
        Dependencies.Compose.navigation,
        Dependencies.Compose.accompanist,
        Dependencies.Compose.coil,
    )

    val koin = listOf(
        Dependencies.Koin.android,
        Dependencies.Koin.compose
    )
}

object Modules {
    const val App = "androidApp"
    object Shared {
        const val root = ":shared"
        const val Core = ":shared:core"
        const val Kt = ":shared:kt"
        const val Commons = ":shared:commons"
        const val Example = ":shared:example"
    }
}