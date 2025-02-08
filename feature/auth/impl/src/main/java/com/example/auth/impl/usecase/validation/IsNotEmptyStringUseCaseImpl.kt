package com.example.auth.impl.usecase.validation

import com.example.auth.api.usecase.validators.IsNotEmptyStringUseCase

class IsNotEmptyStringUseCaseImpl: IsNotEmptyStringUseCase {
    override fun invoke(string: String): Boolean = string.isNotEmpty()
}