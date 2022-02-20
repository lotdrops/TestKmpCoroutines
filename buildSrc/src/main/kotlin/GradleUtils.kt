import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementations(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementations(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementations(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}