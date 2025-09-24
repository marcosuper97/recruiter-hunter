package com.example.recruiterhunter.domain.impl.settings

import com.example.recruiterhunter.domain.interactor.settings.SettingsInteractor
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.domain.services.application_theme.ThemeChangerService

class SettingsInteractorImpl(
    private val themeChangerService: ThemeChangerService
) :
    SettingsInteractor {
    override suspend fun setTheme(theme: ActualTheme) {
        themeChangerService.setTheme(theme)
    }
}