package com.example.recruiterhunter.data.dto.vacancies.elements

import kotlinx.serialization.Serializable

@Serializable
data class SalaryDto(
    val from: Int?,
    val to: Int?,
    val currency: String,
)