package com.example.recruiterhunter.infrastructure.local.datastore.application_theme

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.domain.services.application_theme.ThemeGetterService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeGetterServiceImpl(private val dataStore: DataStore<Preferences>) :
    ThemeGetterService {
    private val themeKey = stringPreferencesKey("theme_key")
    override fun getActualTheme(): Flow<ActualTheme> = dataStore.data
        .map { prefs ->
            ActualTheme.entries.firstOrNull { it.name == prefs[themeKey] } ?: ActualTheme.SYSTEM
        }
}