package com.example.recruiterhunter.presentation.seachVacancyVm.intents

sealed class SearchScreenSideEffects {
    data class OpenDetails(
        val vacancyId: Long,
        val vacancyName: String,
        val employerName: String,
        val employerLogo: String,
        val address: String,
        val salary: String
    ) : SearchScreenSideEffects()

    data object OpenFilters : SearchScreenSideEffects()
    data object DownloadError : SearchScreenSideEffects()
}
