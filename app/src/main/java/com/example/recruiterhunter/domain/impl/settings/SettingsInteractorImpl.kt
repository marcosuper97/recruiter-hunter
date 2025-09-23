package com.example.recruiterhunter.domain.impl.settings

import com.example.recruiterhunter.domain.interactor.settings.SettingsInteractor
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.domain.repository.theme_changer.ThemeChangerRepository

class SettingsInteractorImpl(
    private val themeChangerRepository: ThemeChangerRepository
) :
    SettingsInteractor {
    override suspend fun setTheme(theme: ActualTheme) {
        themeChangerRepository.changeTheme(theme)
    }
}