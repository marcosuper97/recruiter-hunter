package com.example.recruiterhunter.presentation.settings_vm

import com.example.recruiterhunter.domain.model.theme_state.ActualTheme

sealed class SettingsIntent {
    object OpenSettings : SettingsIntent()
    object SendFeedBack : SettingsIntent()
    object OpenSourceCode : SettingsIntent()
    data class SetDarkTheme(val theme: ActualTheme = ActualTheme.DARK) : SettingsIntent()
    data class SetLightTheme(val theme: ActualTheme = ActualTheme.LIGHT) : SettingsIntent()
    data class SetSystemTheme(val theme: ActualTheme = ActualTheme.SYSTEM) : SettingsIntent()
}