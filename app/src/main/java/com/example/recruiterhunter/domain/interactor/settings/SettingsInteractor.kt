package com.example.recruiterhunter.domain.interactor.settings

import com.example.recruiterhunter.domain.model.theme_state.ActualTheme

interface SettingsInteractor {
    suspend fun setTheme(theme: ActualTheme)
}