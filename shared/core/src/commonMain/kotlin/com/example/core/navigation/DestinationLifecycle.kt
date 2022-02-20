package com.example.core.navigation

expect class DestinationLifecycle {
    fun addClearedObserver(observer: DestinationCleared)
}
