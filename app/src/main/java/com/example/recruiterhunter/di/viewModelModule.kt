package com.example.recruiterhunter.di

import com.example.recruiterhunter.presentation.app_theme_vm.AppThemeViewModel
import com.example.recruiterhunter.presentation.settings_vm.SettingsScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AppThemeViewModel(get(), get())
    }

    viewModel {
        SettingsScreenViewModel(get())
    }
}