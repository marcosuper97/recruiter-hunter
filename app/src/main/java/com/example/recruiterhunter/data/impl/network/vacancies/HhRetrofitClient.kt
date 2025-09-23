package com.example.recruiterhunter.data.impl.network.vacancies

import com.example.recruiterhunter.domain.services.request_engine.RequestEngine
import com.example.recruiterhunter.data.dto.vacancies.filteres.areas.AreaDto
import com.example.recruiterhunter.data.dto.vacancies.filteres.industry.IndustryGroupDto
import com.example.recruiterhunter.data.dto.vacancies.response.details.VacancyDetailsResponseDto
import com.example.recruiterhunter.data.dto.vacancies.response.preview.VacanciesResponseDto
import com.example.recruiterhunter.data.impl.network.vacancies.api.HhApi

class HhRetrofitClient(
    private val api: HhApi,
    private val requestEngine: RequestEngine
) : HhNetworkClient {

    override suspend fun detailsVacancyRequest(requestId: Long): Result<VacancyDetailsResponseDto> {
        return requestEngine.doRequest { api.getVacancyDetails(requestId) }
    }

    override suspend fun vacanciesSearchRequest(requestQuery: Map<String, String>): Result<VacanciesResponseDto> {
        return requestEngine.doRequest { api.searchVacancies(requestQuery) }
    }

    override suspend fun getAreas(): Result<List<AreaDto>> {
        return requestEngine.doRequest { api.getAreas() }
    }

    override suspend fun getIndustries(): Result<List<IndustryGroupDto>> {
        return requestEngine.doRequest { api.getIndustries() }
    }
}