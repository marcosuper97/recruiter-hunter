package com.example.recruiterhunter.data.dto.vacancies.response.preview

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacanciesResponseDto(
    val page: Int,
    val pages: Int,
    val found: Long,
    @SerialName("items")
    val vacancies: List<PreviewDto>
)
