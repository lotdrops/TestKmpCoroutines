package com.example.example

import com.example.core.domain.UseCase
import com.example.kt.example.ExampleViewModel
import com.example.core.ui.Clearable
import kotlinx.coroutines.flow.Flow
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

const val EXAMPLE_SCREEN_SCOPE = "exampleScreen"

val exampleModule = module {
    factory<UseCase<Unit, Flow<String>>> { GetExampleDataUseCase() }
    scope(named(EXAMPLE_SCREEN_SCOPE)) {
        scoped<ExampleViewModel> { ExampleViewModelImpl(get(), get(), get()) } bind Clearable::class
    }
}
