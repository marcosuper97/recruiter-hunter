package com.example.recruiterhunter.data.local.data_store.impl

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.recruiterhunter.core.theme_changer.state.ActualTheme
import com.example.recruiterhunter.data.local.data_store.ThemeDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeDataStoreImpl(
    private val dataStore: DataStore<Preferences>
) : ThemeDataStore {
    private val themeKey = stringPreferencesKey("theme_key")
    override fun getTheme(): Flow<ActualTheme> = dataStore.data
        .map { prefs ->
            ActualTheme.entries.firstOrNull { it.name == prefs[themeKey] } ?: ActualTheme.SYSTEM
        }

    override suspend fun setTheme(theme: ActualTheme) {
        dataStore.edit { preferences ->
            preferences[themeKey] = theme.name
        }
    }
}