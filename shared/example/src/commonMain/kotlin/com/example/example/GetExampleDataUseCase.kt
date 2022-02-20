package com.example.example

import com.example.core.domain.UseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetExampleDataUseCase : UseCase<Unit, Flow<String>> {
    override fun invoke(params: Unit): Flow<String> = flow {
        while (true) {
            emit("Main Mock data")
            delay(1500)
            emit("Alternate mock data")
            delay(1500)
        }
    }
}
