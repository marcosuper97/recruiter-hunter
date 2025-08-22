package com.example.recruiterhunter.data.dto.vacancies.filteres.industry

import kotlinx.serialization.Serializable

@Serializable
data class IndustryGroupDto(
    val id: String,
    val name: String,
    val industries: List<IndustryDto>
)
