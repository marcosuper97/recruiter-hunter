package com.example.recruiterhunter.data.impl.network.vacancies.request_builder

import com.example.recruiterhunter.data.dto.vacancies.request.VacancySearchRequest

interface RequestBuildService {
    suspend fun buildRequest(vacancySearchRequest: VacancySearchRequest): Map<String, String>
}