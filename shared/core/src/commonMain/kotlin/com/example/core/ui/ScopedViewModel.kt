package com.example.core.ui

import com.example.core.FrontDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

open class ScopedViewModel(dispatchers: FrontDispatchers) : Clearable {
    protected val scope = CoroutineScope(SupervisorJob() + dispatchers.ui)

    override fun clear() {
        scope.cancel()
    }
}