package com.example.recruiterhunter.domain.interactor.filters

import domain.model.filters.Areas
import domain.model.filters.Filters
import domain.model.filters.Industry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface FiltersInteractor {
    val areasFlow: SharedFlow<Result<List<Areas>>>
    val industryFlow: SharedFlow<Result<List<Industry>>>
    fun fetchFilters(): Flow<Filters>
    fun hasAnyFilters(): Flow<Boolean>

    suspend fun updateFilters(filters: Filters)
    suspend fun clearFilters()
    suspend fun fetchData()
}