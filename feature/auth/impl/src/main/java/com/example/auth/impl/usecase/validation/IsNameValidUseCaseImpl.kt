package com.example.auth.impl.usecase.validation

import com.example.auth.api.usecase.validators.IsNameValidUseCase
import java.util.regex.Pattern

class IsNameValidUseCaseImpl : IsNameValidUseCase {
    override fun invoke(name: String): Boolean {
        return Pattern.matches("^[A-Za-z ]+\$", name)
    }
}