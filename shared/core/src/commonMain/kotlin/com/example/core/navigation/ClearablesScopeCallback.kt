package com.example.core.navigation

import com.example.core.ui.Clearable
import org.koin.core.scope.Scope
import org.koin.core.scope.ScopeCallback

class ClearablesScopeCallback : ScopeCallback {
    override fun onScopeClose(scope: Scope) {
        scope.getAll<Clearable>().forEach { it.clear() }
    }
}
