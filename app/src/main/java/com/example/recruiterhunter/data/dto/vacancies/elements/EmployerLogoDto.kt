package com.example.recruiterhunter.data.dto.vacancies.elements

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployerLogoDto(
    @SerialName("240")
    val path: String,
)