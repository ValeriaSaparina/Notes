package com.example.auth.impl.repository

import com.example.auth.api.datasource.LocalDataSource
import com.example.auth.api.datasource.PreferencesDataSource
import com.example.auth.api.datasource.RemoteDataSource
import com.example.auth.api.repository.UserRepository
import com.example.db.room.entity.UserEntity

class UserRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val preferencesDataSource: PreferencesDataSource,
) : UserRepository {
    override suspend fun signUp(
        email: String,
        name: String,
        password: String
    ): UserEntity {
        val user = remoteDataSource.signUp(name, password, email)
        localDataSource.signIn(user)
        preferencesDataSource.signIn(email)
        return user
    }

    override suspend fun signIn(email: String, password: String): UserEntity {
        val user = remoteDataSource.signIn(email, password)
        localDataSource.signIn(user)
        preferencesDataSource.signIn("${user.id}")
        return user
    }

    override suspend fun getCurrentUser(): UserEntity {
        return localDataSource.getCurrentUser()
    }

    override suspend fun updateUser(user: UserEntity) {
        localDataSource.updateCurrentUser(user)
        remoteDataSource.updateCurrentUser(user)
    }

    override suspend fun signOut() {
        localDataSource.signOut()
        remoteDataSource.signOut()
        preferencesDataSource.signOut()
    }

    override suspend fun isAuth(): String? {
        return preferencesDataSource.isAuth()
    }
}