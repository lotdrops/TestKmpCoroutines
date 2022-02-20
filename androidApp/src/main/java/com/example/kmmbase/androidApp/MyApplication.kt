package com.example.kmmbase.androidApp

import android.app.Application
import com.example.kmmbase.shared.initKoinAndroid
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoinAndroid() {
            androidLogger(Level.ERROR) // fix for: https://github.com/InsertKoinIO/koin/issues/1188n
            androidContext(this@MyApplication)
            // modules(appModule)
        }
    }
}
