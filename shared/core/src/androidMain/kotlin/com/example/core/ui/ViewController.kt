package com.example.core.ui

import com.example.core.FrontDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

actual open class ViewController actual constructor(dispatchers: FrontDispatchers) : Clearable {
    protected actual val scope =
        CoroutineScope(SupervisorJob() + dispatchers.ui)
    actual override fun clear() {
        scope.cancel()
    }
}
