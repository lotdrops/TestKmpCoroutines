package com.example.kmmbase.shared

import org.koin.core.Koin
import org.koin.core.qualifier.named

open class DiScope(val navParams: String, val name: String, val koin: Koin) {
    protected val scope get() = koin.getOrCreateScope(name + navParams, named(name))
    fun close() = scope.close()
}
