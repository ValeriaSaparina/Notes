package com.example.auth.impl.datasource.local

import android.content.SharedPreferences
import com.example.auth.api.datasource.PreferencesDataSource

class SharedPreferencesDataSource(
    private val sharedPreferences: SharedPreferences,
): PreferencesDataSource {
    override suspend fun signIn(id: String) {
        sharedPreferences.edit()
            .putString(SP_USER_ID_KEY, id)
            .apply()
    }

    override suspend fun signOut() {
        sharedPreferences.edit()
            .remove(SP_USER_ID_KEY)
            .apply()
    }

    override suspend fun isAuth(): String? {
        return sharedPreferences.getString(SP_USER_ID_KEY, null)
    }

    companion object {
        private const val SP_USER_ID_KEY = "USER_ID"
    }
}