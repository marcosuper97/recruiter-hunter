package com.example.recruiterhunter.data.network.vacancies

import com.example.recruiterhunter.BuildConfig
import com.example.recruiterhunter.data.dto.vacancies.filteres.areas.AreaDto
import com.example.recruiterhunter.data.dto.vacancies.filteres.industry.IndustryGroupDto
import com.example.recruiterhunter.data.dto.vacancies.response.details.VacancyDetailsResponseDto
import com.example.recruiterhunter.data.dto.vacancies.response.preview.VacanciesResponseDto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface HhApi {
    @Headers(
        "Authorization: Bearer ${BuildConfig.API_KEY}",
        "HH-User-Agent: Vacancy's app/1.0 anannat@yandex.ru"
    )
    @GET("vacancies")
    suspend fun searchVacancies(
        @QueryMap options: Map<String, String>
    ): VacanciesResponseDto

    @Headers(
        "Authorization: Bearer ${BuildConfig.API_KEY}",
        "HH-User-Agent: Vacancy's app/1.0 anannat@yandex.ru"
    )
    @GET("vacancies/{vacancy_id}")
    suspend fun getVacancyDetails(
        @Path("vacancy_id") vacancyId: String
    ): VacancyDetailsResponseDto

    @Headers(
        "Authorization: Bearer ${BuildConfig.API_KEY}",
        "HH-User-Agent: Vacancy's app/1.0 anannat@yandex.ru"
    )
    @GET("industries")
    suspend fun getIndustries(): List<IndustryGroupDto>

    @Headers(
        "Authorization: Bearer ${BuildConfig.API_KEY}",
        "HH-User-Agent: Vacancy's app/1.0 anannat@yandex.ru"
    )
    @GET("areas")
    suspend fun getAreas(): List<AreaDto>
}