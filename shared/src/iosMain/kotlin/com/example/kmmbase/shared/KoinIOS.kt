package com.example.kmmbase.shared

import com.example.kt.example.ExampleViewModel
import com.example.example.EXAMPLE_SCREEN_SCOPE
import com.example.example.ExampleViewModelImpl
import com.example.kmmbase.shared.base.AppViewModel
import com.example.kmmbase.shared.di.initKoin
import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings
import org.koin.core.Koin
import org.koin.dsl.module

fun initDependenciesIos(): DependenciesProvider = initKoin {
    module {
        single<Settings.Factory> { AppleSettings.Factory() }
    }
}.let { DependenciesProvider(it.koin) }

class DependenciesProvider(private val koin: Koin) {
    fun provideAppViewModel(): AppViewModel = koin.get()
    fun provideExampleScope(navParams: String) = ExampleDiScope(navParams, koin)
}

class ExampleDiScope(
    navParams: String,
    koin: Koin
) : DiScope(navParams, EXAMPLE_SCREEN_SCOPE, koin) {
    fun provideExampleViewModel(): ExampleViewModel = scope.get()
}
