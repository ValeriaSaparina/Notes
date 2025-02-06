package com.example.auth.api.usecase

interface IsAuthUseCase {
    suspend operator fun invoke(): Result<String?>
}