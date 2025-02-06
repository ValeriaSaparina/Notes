package com.example.auth.api.datasource

interface PreferencesDataSource {
    suspend fun signIn(id: String)
    suspend fun signOut()
    suspend fun isAuth(): String?
}