pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenCentral()
    }
    
}
rootProject.name = "KMMBase"

//enableFeaturePreview("GRADLE_METADATA")

include(":androidApp")
include(":shared")

include(":shared:core")
include(":shared:example")
include(":shared:commons")
include(":shared:kt")
