package com.example.recruiterhunter.ui.components.vacancy_card

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
@Composable
fun CorrectExample() {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography

    val style = remember(colorScheme, typography) {
        typography.titleMedium.copy(color = colorScheme.onSurface)
    }
}