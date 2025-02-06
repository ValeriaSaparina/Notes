package com.example.auth.impl.datasource.local

import com.example.auth.api.datasource.LocalDataSource
import com.example.db.room.entity.UserEntity
import com.example.db.room.dao.UserDao

class RoomDataSource(
    private val userDao: UserDao,
) : LocalDataSource {
    override suspend fun signIn(user: UserEntity) {
        userDao.createUser(user)
    }

    override suspend fun getCurrentUser(): UserEntity {
        return userDao.getCurrentUser()
    }

    override suspend fun updateCurrentUser(user: UserEntity) {
        userDao.updateCurrentUser(user)
    }

    override suspend fun signOut() {
        userDao.deleteUser()
    }
}