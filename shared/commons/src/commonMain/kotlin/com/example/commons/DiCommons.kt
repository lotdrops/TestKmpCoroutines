package com.example.commons

import com.example.commons.prefs.AppPrefsDataSource
import com.example.commons.prefs.AppPrefsDataSourceImpl
import com.russhwolf.settings.Settings
import org.koin.core.Koin
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val APP_SETTINGS = "APP_SETTINGS"

val commonsModule = module {
    single<Settings.Factory> { getSettingsFactory(getKoin()) }
    single(named(APP_SETTINGS)) { get<Settings.Factory>().create(APP_SETTINGS) }
    factory<AppPrefsDataSource> { AppPrefsDataSourceImpl(get(named(APP_SETTINGS))) }
}

internal expect fun getSettingsFactory(koin: Koin): Settings.Factory
