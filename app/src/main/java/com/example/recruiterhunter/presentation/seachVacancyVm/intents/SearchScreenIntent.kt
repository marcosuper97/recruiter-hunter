package com.example.recruiterhunter.presentation.seachVacancyVm.intents

sealed interface SearchScreenIntent {
    data object LoadNextPage : SearchScreenIntent
    data object DoNewRequest : SearchScreenIntent
}