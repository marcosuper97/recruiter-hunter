package com.example.recruiterhunter.di

import com.example.recruiterhunter.presentation.app_theme_vm.AppThemeViewModel
import org.koin.dsl.module

val viewModelModule = module {
    single {
        AppThemeViewModel(get(),get())
    }
}