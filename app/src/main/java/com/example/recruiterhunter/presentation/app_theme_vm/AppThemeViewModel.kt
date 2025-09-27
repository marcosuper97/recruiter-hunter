package com.example.recruiterhunter.presentation.app_theme_vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.domain.services.application_theme.ThemeGetterService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AppThemeViewModel(
    private val themeGetterService: ThemeGetterService
) : ViewModel() {
    private var _actualThemeState = MutableStateFlow(ActualTheme.SYSTEM)
    val actualThemeState: StateFlow<ActualTheme> get() = _actualThemeState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            themeGetterService.getActualTheme().collect { theme ->
                _actualThemeState.value = theme
            }
        }
    }
}