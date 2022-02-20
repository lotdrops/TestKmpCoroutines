package com.example.core.ui

import com.example.core.FrontDispatchers
import kotlin.native.concurrent.freeze
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

actual open class ViewController actual constructor(dispatchers: FrontDispatchers) : Clearable {
    protected actual val scope =
        CoroutineScope(SupervisorJob() + dispatchers.ui)
    actual override fun clear() {
        scope.cancel()
    }

    fun <T> subscribe(flow: Flow<T>, callback: (T) -> Unit) {
        flow.onEach { callback(it.freeze()) }.launchIn(scope).freeze()
    }
    fun <T> subscribe(
        flow: Flow<T>,
        callback: (T) -> Unit,
        onThrow: (error: Throwable) -> Unit,
    ) {
        flow
            .onEach { callback(it.freeze()) }
            .catch { onThrow(it.freeze()) }
            .launchIn(scope)
            .freeze()
    }
}
