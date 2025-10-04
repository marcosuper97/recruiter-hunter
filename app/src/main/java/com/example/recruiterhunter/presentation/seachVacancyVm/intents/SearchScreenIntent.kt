package com.example.recruiterhunter.presentation.seachVacancyVm.intents

sealed class SearchScreenIntent{
    data class LoadNextPage(val query: String, val page: Int): SearchScreenIntent()
    data class DoNewRequest(val query: String): SearchScreenIntent()
}