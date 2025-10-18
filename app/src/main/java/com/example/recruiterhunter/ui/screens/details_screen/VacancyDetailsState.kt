package com.example.recruiterhunter.ui.screens.details_screen

data class VacancyDetailsState (
    val isLoading: Boolean = true,
    val isError: Boolean = false,
    val content: Boolean = false,
    val isFavorite: Boolean = false,
)