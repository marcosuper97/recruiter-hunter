package com.example.recruiterhunter.domain.interactor.settings

import com.example.recruiterhunter.core.theme_changer.state.ActualTheme

interface SettingsInteractor {
    suspend fun setTheme(theme: ActualTheme)
}