package com.example.core.navigation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

actual class DestinationLifecycle(private val lifecycle: Lifecycle) {
    actual fun addClearedObserver(observer: DestinationCleared) {
        lifecycle.addObserver(object : LifecycleEventObserver {
            override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    observer.invoke()
                }
            }
        })
    }
}
