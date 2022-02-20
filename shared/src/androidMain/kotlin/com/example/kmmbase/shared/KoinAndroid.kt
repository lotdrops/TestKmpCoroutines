package com.example.kmmbase.shared

import com.example.kmmbase.shared.di.initKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoinAndroid(appDeclaration: KoinAppDeclaration = {}) =
    initKoin(appDeclaration)
