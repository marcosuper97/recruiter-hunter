package com.example.recruiterhunter.data.local.data_store

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.recruiterhunter.core.theme_changer.state.ActualTheme
import kotlinx.coroutines.flow.Flow

interface ThemeDataStore {
    fun getTheme(): Flow<ActualTheme>
    suspend fun setTheme(theme: ActualTheme)
}