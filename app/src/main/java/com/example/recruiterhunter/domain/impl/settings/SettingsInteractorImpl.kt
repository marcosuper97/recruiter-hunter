package com.example.recruiterhunter.domain.impl.settings

import com.example.recruiterhunter.domain.actions.send_mail.SendToAction
import com.example.recruiterhunter.domain.actions.settings.SystemSettingsAction
import com.example.recruiterhunter.domain.actions.source_code.OpenSourceCodeAction
import com.example.recruiterhunter.domain.interactor.settings.SettingsInteractor
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.domain.services.application_theme.ThemeChangerService

class SettingsInteractorImpl(
    private val themeChangerService: ThemeChangerService,
    private val sendToAction: SendToAction,
    private val systemSettingsAction: SystemSettingsAction,
    private val openSourceCodeAction: OpenSourceCodeAction

) :
    SettingsInteractor {
    override suspend fun setTheme(theme: ActualTheme) {
        themeChangerService.setTheme(theme)
    }

    override suspend fun sendFeedBack() {
        sendToAction.sendMail()
    }

    override suspend fun openSystemSettings() {
        systemSettingsAction.openSettings()
    }

    override suspend fun openSourceCode() {
        openSourceCodeAction.openRepository()
    }
}