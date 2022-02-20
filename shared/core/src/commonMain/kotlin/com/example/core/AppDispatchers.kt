package com.example.core

import kotlinx.coroutines.CoroutineDispatcher

data class AppDispatchers(
    override val ui: CoroutineDispatcher,
    override val cpu: CoroutineDispatcher,
) : DomainDispatcher, FrontDispatchers

interface FrontDispatchers {
    val ui: CoroutineDispatcher
    val cpu: CoroutineDispatcher
}

interface DomainDispatcher {
    val cpu: CoroutineDispatcher
}
