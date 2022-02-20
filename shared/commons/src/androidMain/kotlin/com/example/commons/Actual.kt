package com.example.commons

import android.app.Application
import com.russhwolf.settings.AndroidSettings
import com.russhwolf.settings.Settings
import org.koin.core.Koin

internal actual fun getSettingsFactory(koin: Koin): Settings.Factory =
    AndroidSettings.Factory((koin.get<Application>()))
