package com.example.recruiterhunter.data.dto.vacancies.elements

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployerRatingDto(
    @SerialName("total_rating")
    val totalRating: String
)
