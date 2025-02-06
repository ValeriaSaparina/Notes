package com.example.auth.api.usecase

import com.example.db.room.entity.UserEntity

interface SignUpUseCase {
    suspend operator fun invoke(
        email: String,
        name: String,
        password: String
    ): Result<UserEntity>
}