
plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    val versionConfig = Version()
    val keystoreProperties = Keystore()

    compileSdk = AndroidSdk.compile
    defaultConfig {
        applicationId = AndroidSdk.applicationId
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        versionCode = versionConfig.versionCode
        versionName = versionConfig.versionName
    }

    lint {
        isCheckReleaseBuilds = false
        isAbortOnError = true
        isWarningsAsErrors = true
        enable("StopShip")
        disable("ObsoleteLintCustomCheck")
        fatal("StopShip")
        xmlReport = true
        xmlOutput = File("reports/lint-results.xml")
    }

    signingConfigs {
        create(ConfigField.BuildTypes.Release) {
            storeFile = File(keystoreProperties.storeFile.trim())
            storePassword = keystoreProperties.storePassword
            keyAlias = keystoreProperties.keyAlias
            keyPassword = keystoreProperties.keyPassword
        }
    }

    buildTypes {
        getByName(ConfigField.BuildTypes.Debug) {
            isDebuggable = true
            isMinifyEnabled = false
        }
        getByName(ConfigField.BuildTypes.Release) {
            isDebuggable = false
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    flavorDimensions.add(ConfigField.Dimension.Default)

    productFlavors {
        create(ConfigField.FlavorProd.Flavor) {
            dimension = ConfigField.Dimension.Default
        }
    }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
        freeCompilerArgs = listOf(
            "-Xallow-jvm-ir-dependencies", "-Xskip-prerelease-check",
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi",
            "-P",
            "plugin:androidx.compose.compiler.plugins.kotlin:" +
                "suppressKotlinVersionCompatibilityCheck=true"
        )
    }
}

dependencies {
    implementation(project(Modules.Shared.root))

    implementations(DependencyGroups.compose)
    implementations(DependencyGroups.koin)
    implementation(Dependencies.Android.lifecycleRuntime)
}
