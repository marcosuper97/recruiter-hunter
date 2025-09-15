package com.example.recruiterhunter.data.network.vacancies.request_builder

import com.example.recruiterhunter.data.dto.vacancies.request.VacancySearchRequest

interface RequestBuildService {
    suspend fun buildRequest(vacancySearchRequest: VacancySearchRequest):Map<String,String>
}