package com.example.recruiterhunter.domain.impl.settings

import com.example.recruiterhunter.domain.data_store.ThemeChanger
import com.example.recruiterhunter.domain.interactor.settings.SettingsInteractor
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme

class SettingsInteractorImpl(
    private val themeChanger: ThemeChanger
) :
    SettingsInteractor {
    override suspend fun setTheme(theme: ActualTheme) {
        themeChanger.setTheme(theme)
    }
}