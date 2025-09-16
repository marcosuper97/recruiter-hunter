package com.example.recruiterhunter.domain.repository.filters

import domain.model.filters.Filters
import kotlinx.coroutines.flow.Flow

interface FiltersDbRepository {
    fun fetchFilters(): Flow<Filters>
    suspend fun updateFilters(filters: Filters)
    suspend fun clearFilters()
    fun hasAnyFilters(): Flow<Boolean>
}