package com.example.recruiterhunter.presentation.seachVacancyVm

import androidx.compose.runtime.Immutable
import com.example.recruiterhunter.domain.model.vacancy.preview.VacancyPreview

@Immutable
data class SearchVacancyState(
    val loading: Boolean = false,
    val loadingNextPage: Boolean = false,
    val hasContent: Boolean = false,
    val vacancyList: VacancyList = VacancyList(),
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

@Immutable
data class VacancyList(
    val itemsList: List<VacancyPreview> = emptyList(),
)
