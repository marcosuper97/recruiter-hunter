package com.example.recruiterhunter.presentation.detailsScreen_vm

import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy

data class VacancyDetailsState(
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val content: Boolean = false,
    val vacancyDetails: Vacancy? = null
)