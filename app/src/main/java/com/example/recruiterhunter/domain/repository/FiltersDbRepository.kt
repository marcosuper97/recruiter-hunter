package com.example.recruiterhunter.domain.repository

import domain.model.filters.Filters
import kotlinx.coroutines.flow.Flow

interface FiltersDbRepository {
    suspend fun fetchFilters(): Flow<Filters>
    suspend fun updateFilters(filters: Filters)
    suspend fun clearFilters()
}