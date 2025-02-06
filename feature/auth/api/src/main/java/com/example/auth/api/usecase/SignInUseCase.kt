package com.example.auth.api.usecase

import com.example.db.room.entity.UserEntity

interface SignInUseCase {
    suspend operator fun invoke(email: String, password: String): Result<UserEntity>
}