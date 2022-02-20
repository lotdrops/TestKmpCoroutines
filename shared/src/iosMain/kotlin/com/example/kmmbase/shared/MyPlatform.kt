package com.example.kmmbase.shared

import platform.UIKit.UIDevice

actual class MyPlatform {
    actual val value: String = UIDevice.currentDevice.systemName() + " " +
        UIDevice.currentDevice.systemVersion
}
