package com.sendiko.librariestesting.core.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPreferences(private val dataStore: DataStore<Preferences>) {

    private val darkThemeKey = booleanPreferencesKey("dark_theme")
    private val themeBasedKey = booleanPreferencesKey("theme_by_system")

    suspend fun setThemeBase(isSystemBased: Boolean) {
        dataStore.edit {
            it[themeBasedKey] = isSystemBased
        }
    }

    fun getThemeBase(): Flow<Boolean> {
        return dataStore.data.map {
            it[themeBasedKey] ?: true
        }
    }

    suspend fun setTheme(isDarkTheme: Boolean) {
        dataStore.edit {
            it[darkThemeKey] = isDarkTheme
        }
    }

    fun getTheme(): Flow<Boolean> {
        return dataStore.data.map {
            it[darkThemeKey] ?: false
        }
    }

}