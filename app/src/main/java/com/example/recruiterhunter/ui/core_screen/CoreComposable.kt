package com.example.recruiterhunter.ui.core_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        Surface {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Column {
                    Text(
                        text = "Всем привет, а вот и я",
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            }
        }
    }
}