package com.example.recruiterhunter.data.converters.filters

import com.example.recruiterhunter.data.local.filters.entity.FiltersEntity
import domain.model.filters.Filters

interface FiltersConverter {
    suspend fun map(filtersEntity: FiltersEntity): Filters
    suspend fun map(filters: Filters): FiltersEntity
}