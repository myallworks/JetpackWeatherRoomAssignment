package dev.shrishri1108.jetpackweaterroomassignment.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore("auth_prefs")

class AuthRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val USER_LOGGED_IN = booleanPreferencesKey("user_logged_in")
    private val USER_EMAIL = stringPreferencesKey("user_email")

    suspend fun login(username: String, password: String): Boolean {
        return if (username == "testapp@google.com" && password == "Test@123456") {
            saveLoginState(true, username)
            true
        } else {
            false
        }
    }

    suspend fun logout() {
        saveLoginState(false, "")
    }

    private suspend fun saveLoginState(isLoggedIn: Boolean, email: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_LOGGED_IN] = isLoggedIn
            preferences[USER_EMAIL] = email
        }
    }

    val isLoggedIn: Flow<Boolean> = context.dataStore.data
        .map { preferences -> preferences[USER_LOGGED_IN] ?: false }

    val loggedInUserEmail: Flow<String?> = context.dataStore.data
        .map { preferences -> preferences[USER_EMAIL] }
}
