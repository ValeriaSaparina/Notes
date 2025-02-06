package com.example.auth.api.datasource

import com.example.db.room.entity.UserEntity

interface RemoteDataSource {
    suspend fun signIn(email: String, password: String): UserEntity

    suspend fun signUp(
        name: String,
        password: String,
        email: String
    ): UserEntity
    suspend fun getCurrentUser(): UserEntity
    suspend fun updateCurrentUser(user: UserEntity)
    suspend fun signOut()
}