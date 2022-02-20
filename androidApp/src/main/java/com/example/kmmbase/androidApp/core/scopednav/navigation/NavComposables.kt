package com.example.kmmbase.androidApp.core.scopednav.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.core.navigation.ClearablesScopeCallback
import com.example.core.navigation.DestinationLifecycle
import com.example.core.navigation.ScopeLifecycleHandler
import org.koin.androidx.compose.getKoin
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

/**
 * Substitute for navigation
 * This helper function can be used for declaring nested navigation graphs when we want to have
 * a scope bound to that nested graph.
 * It is only for convenience, as it provides the NestedNavGraph that can be used by the composables
 * in that graph.
 */
inline fun <reified T : NestedNavGraph<*, *>> NavGraphBuilder.scopedNavigation(
    nestedNavGraph: T,
    crossinline builder: NavGraphBuilder.(T) -> Unit
): Unit = navigation(nestedNavGraph.startDestination.declaredPath, nestedNavGraph.declaredPath) {
    builder(nestedNavGraph)
}

/**
 * Substitute for composable
 * This helper function is used to declare any screen for which we want to have a scope.
 */
inline fun <reified T : NavDestination<*>> NavGraphBuilder.scopedComposable(
    destination: T,
    crossinline content: @Composable (NavBackStackEntry, Scope) -> Unit,
) {
    composable(destination.declaredPath, destination.namedNavArgs) { navEntry ->
        val args = navEntry.arguments
        val pathWithArgs = destination.buildPath(args)
        val koinScope = getKoin().getOrCreateScope(pathWithArgs, named(destination.pathRoot))
        koinScope.registerCallback(ClearablesScopeCallback())

        RunOnce {
            ScopeLifecycleHandler().bind(koinScope, DestinationLifecycle(navEntry.lifecycle))
        }

        content(navEntry, koinScope)
    }
}

/**
 * Substitute for composable in nested graphs
 * This helper function is used to declare screens in nested graphs, when we want the scope of
 * the nested graph and the scope of this screen.
 */
inline fun <reified T : NavDestination<*>, reified P : NestedNavGraph<*, *>>
NavGraphBuilder.doubleScopedComposable(
    navController: NavController,
    parentGraph: P,
    destination: T,
    crossinline content: @Composable (NavBackStackEntry, parentScope: Scope, Scope) -> Unit,
) {
    composable(destination.declaredPath) { navEntry ->
        val parentScope = getKoin()
            .getOrCreateScope(parentGraph.declaredPath, named(parentGraph.pathRoot))

        val args = navEntry.arguments
        val pathWithArgs = destination.buildPath(args)
        val koinScope = getKoin().getOrCreateScope<T>(pathWithArgs)

        RunOnce {
            val parentEntry = navEntry.getParentEntry(navController)
            if (parentEntry != null) {
                parentScope.getOrNull<ScopeLifecycleHandler>()?.bind(
                    scope = parentScope,
                    lifecycle = DestinationLifecycle(parentEntry.lifecycle),
                )
            }

            ScopeLifecycleHandler().bind(koinScope, DestinationLifecycle(navEntry.lifecycle))
        }

        content(navEntry, parentScope, koinScope)
    }
}

fun NavBackStackEntry.getParentEntry(navController: NavController) =
    destination.parent?.id?.let { parentId ->
        navController.getBackStackEntry(parentId)
    }

fun NavBackStackEntry.getParentOrThis(navController: NavController) =
    getParentEntry(navController) ?: this

/**
 * Hack so that an effect can be run only once and not on each composition.
 */
@Composable
fun RunOnce(composable: () -> Unit) {
    rememberSaveable {
        composable()
        true
    }
}
