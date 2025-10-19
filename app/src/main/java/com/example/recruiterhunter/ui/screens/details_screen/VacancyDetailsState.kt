package com.example.recruiterhunter.ui.screens.details_screen

import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy

data class VacancyDetailsState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val content: Boolean = false,
    val isFavorite: Boolean = false,
    val vacancyDetails: Vacancy? = null
)