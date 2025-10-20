package com.example.recruiterhunter.presentation.detailsScreen_vm

sealed class VacancyDetailsIntent {
    object MarkFavorite : VacancyDetailsIntent()
    object ShareVacancy : VacancyDetailsIntent()
    data class FetchDetails(val vacancyId: Long) : VacancyDetailsIntent()
}