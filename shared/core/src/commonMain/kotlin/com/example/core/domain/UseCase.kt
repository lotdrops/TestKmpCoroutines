package com.example.core.domain

interface UseCase<Params, Return> {
    operator fun invoke(params: Params): Return
}
