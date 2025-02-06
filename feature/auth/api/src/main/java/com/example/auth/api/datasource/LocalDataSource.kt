package com.example.auth.api.datasource

import com.example.db.room.entity.UserEntity


interface LocalDataSource {
    suspend fun signIn(user: UserEntity)
    suspend fun getCurrentUser(): UserEntity
    suspend fun updateCurrentUser(user: UserEntity)
    suspend fun signOut()
}