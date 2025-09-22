package com.example.recruiterhunter.data.local.data_store

import com.example.recruiterhunter.core.theme_changer.state.ActualTheme
import kotlinx.coroutines.flow.Flow

interface ThemeDataStore {
    fun getTheme(): Flow<ActualTheme>
    suspend fun changeDark(theme: ActualTheme)
}