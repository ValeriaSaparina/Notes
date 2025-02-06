package com.example.db.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.db.room.entity.UserEntity

@Dao
interface UserDao {
    @Insert
    fun createUser(user: UserEntity)

    @Query("SELECT * FROM users LIMIT 1")
    fun getCurrentUser(): UserEntity

    @Update
    fun updateCurrentUser(user: UserEntity)

    @Query("DELETE FROM users")
    fun deleteUser()
}