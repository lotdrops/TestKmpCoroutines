package com.example.commons.prefs

import com.russhwolf.settings.Settings

interface AppPrefsDataSource {
    fun getNumber(): Int
    fun setNumber(value: Int)
}

internal class AppPrefsDataSourceImpl(private val appSettings: Settings) : AppPrefsDataSource {
    override fun getNumber() = appSettings.getInt("appNumber", 0)

    override fun setNumber(value: Int) {
        appSettings.putInt("appNumber", value)
    }
}
