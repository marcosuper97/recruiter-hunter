package com.example.recruiterhunter.data.network.vacancies

import com.example.recruiterhunter.BuildConfig
import com.example.recruiterhunter.data.dto.vacancies.filteres.areas.AreaDto
import com.example.recruiterhunter.data.dto.vacancies.filteres.industry.IndustryGroupDto
import com.example.recruiterhunter.data.dto.vacancies.response.full.FullDetailsDto
import com.example.recruiterhunter.data.dto.vacancies.response.preview.VacancyResponseDto
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
    ): VacancyResponseDto

    @Headers(
        "Authorization: Bearer ${BuildConfig.API_KEY}",
        "HH-User-Agent: Vacancy's app/1.0 anannat@yandex.ru"
    )
    @GET("vacancies/{vacancy_id}")
    suspend fun getVacancyDetails(
        @Path("vacancy_id") vacancyId: String
    ): FullDetailsDto

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