package com.example.recruiterhunter.ui.screens.core_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.recruiterhunter.presentation.app_theme_vm.AppThemeViewModel
import com.example.recruiterhunter.ui.components.settings.ButtonsGroup
import com.example.recruiterhunter.ui.theme.RecruiterHunterTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoreCompose(
    viewModel: AppThemeViewModel = koinViewModel()
) {
    val actualTheme by viewModel.actualThemeState.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    RecruiterHunterTheme(actualTheme) {
        Surface {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                ButtonsGroup(actualTheme) { selectedTheme ->
                    scope.launch { viewModel.themeChange(selectedTheme) }
                }
            }
        }
    }
}