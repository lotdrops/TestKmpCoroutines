package com.example.core.ui

import com.example.core.FrontDispatchers
import kotlinx.coroutines.CoroutineScope

expect open class ViewController(dispatchers: FrontDispatchers) {
    protected val scope: CoroutineScope

    fun clear()
}
