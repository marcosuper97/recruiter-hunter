package com.example.recruiterhunter.presentation.settings_vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruiterhunter.domain.interactor.settings.SettingsInteractor
import kotlinx.coroutines.launch

class SettingsScreenViewModel(
    private val settingsInteractor: SettingsInteractor
) : ViewModel() {

    fun sendIntent(intent: SettingsIntent) {
        viewModelScope.launch { reducer(intent) }
    }

    private suspend fun reducer(intent: SettingsIntent) {
        when (intent) {
            SettingsIntent.OpenSettings -> settingsInteractor.openSystemSettings()
            SettingsIntent.OpenSourceCode -> settingsInteractor.openSourceCode()
            SettingsIntent.SendFeedBack -> settingsInteractor.sendFeedBack()
            is SettingsIntent.SetDarkTheme -> settingsInteractor.setTheme(intent.theme)
            is SettingsIntent.SetLightTheme -> settingsInteractor.setTheme(intent.theme)
            is SettingsIntent.SetSystemTheme -> settingsInteractor.setTheme(intent.theme)
        }
    }
}