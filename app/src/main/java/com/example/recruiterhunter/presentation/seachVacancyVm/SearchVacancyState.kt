package com.example.recruiterhunter.presentation.seachVacancyVm

import com.example.recruiterhunter.domain.model.vacancy.preview.VacancyPreview

data class SearchVacancyState(
    val loading: Boolean = false,
    val loadingNextPage: Boolean = false,
    val hasContent: Boolean = false,
    val vacancyList: List<VacancyPreview> = emptyList(),
    val vacanciesFounded: Long = 0L,
    val error: Boolean = false,
    val errorMessage: String? = "",
    val hasAnyFilters: Boolean = false,
)
