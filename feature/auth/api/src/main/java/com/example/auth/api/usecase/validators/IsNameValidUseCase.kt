package com.example.auth.api.usecase.validators

interface IsNameValidUseCase {
    operator fun invoke(name: String): Boolean
}