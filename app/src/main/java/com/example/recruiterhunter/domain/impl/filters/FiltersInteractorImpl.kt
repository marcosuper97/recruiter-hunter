package com.example.recruiterhunter.domain.impl.filters

import com.example.recruiterhunter.domain.interactor.filters.FiltersInteractor
import com.example.recruiterhunter.domain.repository.filters.FiltersDbRepository
import com.example.recruiterhunter.domain.repository.filters.FiltersNetworkRepository
import domain.model.filters.Areas
import domain.model.filters.Filters
import domain.model.filters.Industry
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow

class FiltersInteractorImpl(
    private val filtersDbRepository: FiltersDbRepository,
    private val filtersNetworkRepository: FiltersNetworkRepository,
) : FiltersInteractor {
    private val _areasFlow = MutableSharedFlow<Result<List<Areas>>>(replay = 1)
    private val _industryFlow = MutableSharedFlow<Result<List<Industry>>>(replay = 1)

    override val areasFlow: SharedFlow<Result<List<Areas>>> get() = _areasFlow
    override val industryFlow: SharedFlow<Result<List<Industry>>> get() = _industryFlow

    override suspend fun fetchFilters(): Flow<Filters> = filtersDbRepository.fetchFilters()

    override suspend fun fetchData() {
        val areasDate = filtersNetworkRepository.getAreas()
        val industryDate = filtersNetworkRepository.getIndustries()
        _areasFlow.emit(areasDate)
        _industryFlow.emit(industryDate)
    }

    override suspend fun updateFilters(filters: Filters) {
        filtersDbRepository.updateFilters(filters)
    }

    override suspend fun clearFilters() {
        filtersDbRepository.clearFilters()
    }

}