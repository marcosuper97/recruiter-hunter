package com.example.recruiterhunter.presentation.seachVacancyVm

import com.example.recruiterhunter.domain.model.vacancy.preview.VacancyPreview

data class SearchVacancyState(
    val loading: Boolean = false,
    val loadingNextPage: Boolean = false,
    val hasContent: Boolean = false,
    val vacancyList: List<VacancyPreview> = emptyList(),
    val vacanciesFounded: Long = 0L,
    val emptyResult: Boolean = false,
    val authorizationError: Boolean = false,
    val serverError: Boolean = false,
    val clientError: Boolean = false,
    val unknownError: Boolean = false,
    val networkError: Boolean = false,
    val internetHasNotAvailable: Boolean = false,
    val hasAnyFilters: Boolean = false,
)
