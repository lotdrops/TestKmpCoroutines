package com.example.core.domain

interface UseCaseSuspend<Params, Return> {
    suspend operator fun invoke(params: Params): Return
}
