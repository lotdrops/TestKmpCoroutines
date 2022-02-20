package com.example.kmmbase.shared

class Greeting {
    fun greeting(): String {
        return "Hello, ${MyPlatform().value}!"
    }
}
