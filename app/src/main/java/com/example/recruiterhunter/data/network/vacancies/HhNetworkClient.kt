package com.example.recruiterhunter.data.network.vacancies

import com.example.recruiterhunter.data.dto.vacancies.filteres.areas.AreaDto
import com.example.recruiterhunter.data.dto.vacancies.filteres.industry.IndustryGroupDto
import com.example.recruiterhunter.data.dto.vacancies.response.full.VacancyDetailsResponseDto
import com.example.recruiterhunter.data.dto.vacancies.response.preview.VacanciesResponseDto

interface HhNetworkClient {
    suspend fun detailsVacancyRequest(requestId: String): Result<VacancyDetailsResponseDto>
    suspend fun vacanciesSearchRequest(requestQuery: Map<String, String>): Result<VacanciesResponseDto>
    suspend fun getAreas(): Result<List<AreaDto>>
    suspend fun getIndustries(): Result<List<IndustryGroupDto>>
}