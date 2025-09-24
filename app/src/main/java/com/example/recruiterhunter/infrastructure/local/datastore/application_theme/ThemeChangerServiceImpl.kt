package com.example.recruiterhunter.infrastructure.local.datastore.application_theme

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.domain.services.application_theme.ThemeChangerService

class ThemeChangerServiceImpl(private val dataStore: DataStore<Preferences>) : ThemeChangerService {
    private val themeKey = stringPreferencesKey("theme_key")
    override suspend fun setTheme(actualTheme: ActualTheme) {
        dataStore.edit { preferences ->
            preferences[themeKey] = actualTheme.name
        }
    }
}