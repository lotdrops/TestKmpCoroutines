package com.example.core.navigation

import org.koin.core.scope.Scope

class ScopeLifecycleHandler {
    private var boundToLifecycle = false

    fun bind(scope: Scope, lifecycle: DestinationLifecycle) {
        if (!boundToLifecycle) {
            boundToLifecycle = true
            scope.registerCallback(ClearablesScopeCallback())
            lifecycle.addClearedObserver {
                scope.close()
            }
        }
    }
}

fun interface DestinationCleared {
    fun invoke()
}
