package com.example.kmmbase.shared.di

import com.example.commons.commonsModule
import com.example.core.AppDispatchers
import com.example.core.DomainDispatcher
import com.example.core.FrontDispatchers
import com.example.example.exampleModule
import com.example.kmmbase.shared.base.AppViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.bind
import org.koin.dsl.module

internal fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            commonModule(),
            exampleModule,
            commonsModule,
        )
    }

private val appScope = CoroutineScope(SupervisorJob())

fun commonModule() = module {
    single { AppViewModel(get()) }

    single { appScope }

    single {
        AppDispatchers(
            get(named(DiConstants.Dispatchers.UI)),
            get(named(DiConstants.Dispatchers.CPU)),
        )
    } bind DomainDispatcher::class bind FrontDispatchers::class
    single<CoroutineDispatcher>(named(DiConstants.Dispatchers.UI)) { Dispatchers.Main.immediate }
    single(named(DiConstants.Dispatchers.CPU)) { Dispatchers.Default }
}
