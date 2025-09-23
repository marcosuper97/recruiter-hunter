package com.example.recruiterhunter.data.impl.filters

import com.example.recruiterhunter.data.converters.filters.FiltersConverter
import com.example.recruiterhunter.infrastructure.local.roomdb.filters.dao.FiltersDao
import com.example.recruiterhunter.infrastructure.local.roomdb.filters.entity.FiltersEntity
import com.example.recruiterhunter.domain.repository.filters.FiltersDbRepository
import domain.model.filters.Filters
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FiltersDbRepositoryImpl(
    private val filtersConverter: FiltersConverter,
    private val filtersDao: FiltersDao
) : FiltersDbRepository {
    override fun fetchFilters(): Flow<Filters> =
        filtersDao.getFilters().map { filtersEntity ->
            filtersConverter.map(filtersEntity)
        }

    override suspend fun updateFilters(filters: Filters) {
        filtersDao.update(filtersConverter.map(filters))
    }

    override suspend fun clearFilters() {
        filtersDao.update(CLEAR_FILTERS)
    }

    override fun hasAnyFilters(): Flow<Boolean> = filtersDao.hasAnyFilters()

    companion object {
        private val CLEAR_FILTERS = FiltersEntity()
    }
}