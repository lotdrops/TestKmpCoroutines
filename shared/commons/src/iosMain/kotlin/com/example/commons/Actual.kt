package com.example.commons

import com.russhwolf.settings.AppleSettings
import com.russhwolf.settings.Settings
import org.koin.core.Koin

internal actual fun getSettingsFactory(koin: Koin): Settings.Factory = AppleSettings.Factory()
