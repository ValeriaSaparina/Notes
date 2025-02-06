package com.example.auth.impl.usecase.validation

import android.util.Patterns
import com.example.auth.api.usecase.validators.IsEmailValidUseCase

class IsEmailValidUseCaseImpl: IsEmailValidUseCase {
    override fun invoke(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}