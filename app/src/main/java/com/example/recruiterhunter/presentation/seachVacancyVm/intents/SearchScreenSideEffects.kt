package com.example.recruiterhunter.presentation.seachVacancyVm.intents

sealed class SearchScreenSideEffects {
    data class OpenDetails(
        val route: String
    ) : SearchScreenSideEffects()
    data object OpenFilters : SearchScreenSideEffects()
    data object DownloadError : SearchScreenSideEffects()
}
