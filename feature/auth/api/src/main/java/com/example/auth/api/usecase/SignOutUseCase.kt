package com.example.auth.api.usecase

interface SignOutUseCase {
    suspend operator fun invoke(): Result<Unit>
}