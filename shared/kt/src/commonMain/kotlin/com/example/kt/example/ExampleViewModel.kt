package com.example.kt.example

import com.example.core.ui.Clearable
import kotlinx.coroutines.flow.Flow

interface ExampleViewModel : Clearable {
    val data: Flow<String>
}
