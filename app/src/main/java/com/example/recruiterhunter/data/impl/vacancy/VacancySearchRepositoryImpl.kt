package com.example.recruiterhunter.data.impl.vacancy

import com.example.recruiterhunter.data.converters.vacancy.preview.VacanciesPreviewConverter
import com.example.recruiterhunter.data.dto.vacancies.request.VacancySearchRequest
import com.example.recruiterhunter.data.local.roomdb.filters.dao.FiltersDao
import com.example.recruiterhunter.data.network.vacancies.HhNetworkClient
import com.example.recruiterhunter.data.network.vacancies.request_builder.RequestBuildService
import domain.model.vacancy.VacanciesList
import domain.repository.VacancySearchRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class VacancySearchRepositoryImpl(
    private val hhNetworkClient: HhNetworkClient,
    private val vacanciesPreviewConverter: VacanciesPreviewConverter,
    private val requestBuildService: RequestBuildService,
    private val filtersDao: FiltersDao
) : VacancySearchRepository {
    private val filtersFlow = filtersDao.getFilters().map { filters ->
        VacancySearchRequest(
            area = filters.area,
            industry = filters.industry,
            salary = filters.salary,
            onlyWithSalary = filters.onlyWithSalary == true,
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
}