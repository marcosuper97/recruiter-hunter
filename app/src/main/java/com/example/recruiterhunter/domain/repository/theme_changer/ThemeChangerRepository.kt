package com.example.recruiterhunter.domain.repository.theme_changer

import com.example.recruiterhunter.core.theme_changer.state.ActualTheme
import kotlinx.coroutines.flow.Flow

interface ThemeChangerRepository {
    fun getTheme(): Flow<ActualTheme>
    suspend fun changeTheme(theme: ActualTheme)
}