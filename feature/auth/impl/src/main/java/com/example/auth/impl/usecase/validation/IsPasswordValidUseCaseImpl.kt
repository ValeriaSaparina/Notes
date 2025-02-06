package com.example.auth.impl.usecase.validation

import com.example.auth.api.usecase.validators.IsPasswordValidUseCase
import java.util.regex.Pattern

class IsPasswordValidUseCaseImpl : IsPasswordValidUseCase {
    override fun invoke(password: String): Boolean {
        return Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$", password)
    }
}