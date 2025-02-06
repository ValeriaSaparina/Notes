package com.example.auth.api.usecase.validators

interface IsPasswordValidUseCase {
    operator fun invoke(password: String): Boolean
}