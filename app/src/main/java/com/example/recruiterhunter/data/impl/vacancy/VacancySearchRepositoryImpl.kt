package com.example.recruiterhunter.data.impl.vacancy

import android.util.Log
import com.example.recruiterhunter.data.converters.vacancy.preview.VacanciesPreviewConverter
import com.example.recruiterhunter.data.dto.vacancies.request.VacancySearchRequest
import com.example.recruiterhunter.data.impl.network.vacancies.HhNetworkClient
import com.example.recruiterhunter.data.impl.network.vacancies.request_builder.RequestBuildService
import com.example.recruiterhunter.infrastructure.local.roomdb.filters.dao.FiltersDao
import com.example.recruiterhunter.infrastructure.local.roomdb.filters.entity.FiltersEntity
import domain.model.vacancy.VacanciesList
import domain.repository.VacancySearchRepository
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class VacancySearchRepositoryImpl(
    private val hhNetworkClient: HhNetworkClient,
    private val vacanciesPreviewConverter: VacanciesPreviewConverter,
    private val requestBuildService: RequestBuildService,
    private val filtersDao: FiltersDao
) : VacancySearchRepository {
    private val filtersFlow = filtersDao.getFilters()
        .onEach { filters ->
            Log.d("он ич", "Как вы поняли, ничего не работает")
            if (filters == null) {
                filtersDao.insert(FiltersEntity())
            }
        }
        .map { filters ->
            Log.d("Дошли до запроса во флоу", "Как вы поняли, ничего не работает")
            VacancySearchRequest(
                area = filters?.area,
                industry = filters?.industry,
                salary = filters?.salary,
                onlyWithSalary = filters?.onlyWithSalary == true,
                text = ""
            )
        }

    override suspend fun doRequest(query: String, page: Int): Result<VacanciesList> =
        hhNetworkClient.vacanciesSearchRequest(
            requestBuildService.buildRequest(
                filtersFlow
                    .first()
                    .copy(text = query, page = page)
            )
        ).map { response -> vacanciesPreviewConverter.map(response) }
            .onFailure { exception -> Log.d("Ошибка",exception.toString() ) }
}