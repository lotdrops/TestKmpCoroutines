import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.io.File

object AndroidSdk {
    const val min = 24
    const val compile = 31
    const val target = compile
    const val buildToolsVersion = "30.0.2"
    const val applicationId = "com.example.kmmbase"
}

class Version {
    private val major = 1
    private val minor = 0
    private val patch = 0
    private val build = 0
    private val buildText = ""

    val versionCode = major * 100000 + minor * 1000 + patch * 10 + build
    val versionName = "$major.$minor.${patch}$buildText"
}

object ConfigField {
    object Dimension {
        const val Default = "default"
    }

    object BuildTypes {
        const val Debug = "debug"
        const val Release = "release"
    }

    object Type {
        const val STRING = "String"
        const val BOOLEAN = "boolean"
    }

    object FlavorProd {
        const val Flavor = "prod"
    }
}

fun Project.sharedCommonAndroidConfig() {
    this.extensions.getByType<BaseExtension>().androidBlock()
}

private fun BaseExtension.androidBlock() {
    compileSdkVersion(AndroidSdk.compile)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
    }
}