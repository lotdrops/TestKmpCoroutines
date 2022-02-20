package com.example.kmmbase.shared

actual class MyPlatform {
    actual val value: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}
