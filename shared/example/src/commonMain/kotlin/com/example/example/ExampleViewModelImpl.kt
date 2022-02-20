package com.example.example

import com.example.commons.prefs.AppPrefsDataSource
import com.example.core.FrontDispatchers
import com.example.core.domain.UseCase
import com.example.kt.example.ExampleViewModel
import com.example.core.ui.ScopedViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class ExampleViewModelImpl(
    dispatchers: FrontDispatchers,
    getExampleDataUseCase: UseCase<Unit, Flow<String>>,
    private val testDataSource: AppPrefsDataSource,
) : ScopedViewModel(dispatchers), ExampleViewModel {
//generar codi ios i mirar si funciona amb KtExampleViewModel, i si es pot veure el Impl també. Posar internal si es pot veure
    override val data = getExampleDataUseCase(Unit).map { it + testDataSource.getNumber() }

    init {
        scope.launch {
            while (true) {
                delay(1000)
                testDataSource.setNumber(testDataSource.getNumber() + 1)
            }
        }
    }
}
