package com.example.recruiterhunter.ui.screens.details_screen

sealed class VacancyDetailsIntent {
    object MarkFavorite: VacancyDetailsIntent()
    object ShareVacancy: VacancyDetailsIntent()
}