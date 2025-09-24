package com.example.recruiterhunter.domain.services.application_theme

import com.example.recruiterhunter.domain.model.theme_state.ActualTheme

interface ThemeChangerService {
    suspend fun setTheme(actualTheme: ActualTheme)
}