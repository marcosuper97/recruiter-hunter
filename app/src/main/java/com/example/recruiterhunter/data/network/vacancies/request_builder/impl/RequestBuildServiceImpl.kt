package com.example.recruiterhunter.data.network.vacancies.request_builder.impl

import com.example.recruiterhunter.data.dto.vacancies.request.VacancySearchRequest
import com.example.recruiterhunter.data.network.vacancies.request_builder.RequestBuildService

class RequestBuildServiceImpl(): RequestBuildService {
    override suspend fun buildRequest(
        vacancySearchRequest: VacancySearchRequest
    ): Map<String, String> = buildMap {
        put("page", vacancySearchRequest.page.toString())
        put("per_page", vacancySearchRequest.perPage)
        put("text", vacancySearchRequest.text)
        vacancySearchRequest.area?.let {
            put("area", vacancySearchRequest.area)
        }
        vacancySearchRequest.industry?.let {
            put("industry", vacancySearchRequest.industry)
        }
        vacancySearchRequest.salary?.let {
            put("salary", vacancySearchRequest.salary.toString())
        }
        if (vacancySearchRequest.onlyWithSalary) {
            put("only_with_salary", "true")
        }
    }
}