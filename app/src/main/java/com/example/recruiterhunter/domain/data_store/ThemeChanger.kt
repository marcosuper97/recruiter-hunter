package com.example.recruiterhunter.domain.data_store

import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import kotlinx.coroutines.flow.Flow

interface ThemeChanger {
    fun getTheme(): Flow<ActualTheme>
    suspend fun setTheme(theme: ActualTheme)
}