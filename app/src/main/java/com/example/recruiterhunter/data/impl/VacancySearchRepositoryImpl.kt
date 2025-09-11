package com.example.recruiterhunter.data.impl

import com.example.recruiterhunter.data.converters.vacancy.preview.VacanciesPreviewConverter
import com.example.recruiterhunter.data.network.vacancies.HhRetrofitClient
import domain.repository.VacancySearchRepository

class VacancySearchRepositoryImpl(
    private val hhRetrofitClient: HhRetrofitClient,
    private val vacanciesPreviewConverter: VacanciesPreviewConverter
) : VacancySearchRepository {
    override suspend fun doRequest(query: String, page: Int) {
        TODO("Not yet implemented")
    }
}