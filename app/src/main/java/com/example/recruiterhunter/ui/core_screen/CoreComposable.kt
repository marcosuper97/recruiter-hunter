package com.example.recruiterhunter.ui.core_screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recruiterhunter.presentation.app_theme_vm.AppThemeViewModel
import com.example.recruiterhunter.ui.theme.RecruiterHunterTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoreCompose(
    viewModel: AppThemeViewModel = koinViewModel()
) {
    val actualTheme by viewModel.actualThemeState.collectAsStateWithLifecycle()
    RecruiterHunterTheme(actualTheme) {

    }
}