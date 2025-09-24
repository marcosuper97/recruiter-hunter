package com.example.recruiterhunter.domain.services.application_theme

import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import kotlinx.coroutines.flow.Flow

interface ThemeGetterService {
    fun getActualTheme(): Flow<ActualTheme>
}