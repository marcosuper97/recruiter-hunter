package com.example.recruiterhunter.di

import com.example.recruiterhunter.presentation.app_theme_vm.AppThemeViewModel
import com.example.recruiterhunter.presentation.detailsScreen_vm.DetailsScreenViewModel
import com.example.recruiterhunter.presentation.seachVacancyVm.SearchVacancyViewModel
import com.example.recruiterhunter.presentation.settings_vm.SettingsScreenViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        AppThemeViewModel(get())
    }

    viewModel {
        SettingsScreenViewModel(get())
    }

    viewModel {
        SearchVacancyViewModel(get(), get())
    }

    viewModel{
        DetailsScreenViewModel(get())
    }
}