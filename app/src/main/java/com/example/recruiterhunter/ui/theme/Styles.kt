package com.example.recruiterhunter.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun vacancyDetailsTypo(): VacancyTypography {
    val themeTypo = MaterialTheme.typography
    val themeColors = MaterialTheme.colorScheme

    return remember(themeTypo, themeColors) {
        VacancyTypography(
            cardVacancyNameStyle = themeTypo.titleMedium.copy(
                color = themeColors.onSurface,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            ),
            vacancyEmployerStyle = themeTypo.labelLarge.copy(
                color = themeColors.onSurface,
                lineHeight = 16.sp,
                fontWeight = FontWeight.Light,
            ),
            addressTextStyle = themeTypo.labelMedium.copy(
                lineHeight = 14.sp,
                color = themeColors.onSurface,
            ),
            salaryTextStyle = themeTypo.bodyLarge.copy(
                color = themeColors.tertiary,
                fontWeight = FontWeight.Bold
            ),
            vacancyKeySkills = themeTypo.labelMedium.copy(
                color = themeColors.onSurface
            ),
            detailsTitleText = themeTypo.titleSmall.copy(
                color = themeColors.onSurface,
                lineHeight = 18.sp,
                fontWeight = FontWeight.Bold
            ),
            detailsText = themeTypo.titleSmall.copy(
                color = themeColors.onSurface,
                lineHeight = 16.sp,
            ),
        )
    }
}

data class VacancyTypography(
    val cardVacancyNameStyle: TextStyle,
    val vacancyEmployerStyle: TextStyle,
    val addressTextStyle: TextStyle,
    val salaryTextStyle: TextStyle,
    val vacancyKeySkills: TextStyle,
    val detailsTitleText: TextStyle,
    val detailsText: TextStyle,
)