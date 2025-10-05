package com.example.recruiterhunter.data.dto.vacancies.elements

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployerDto(
    val name: String,
    @SerialName("logo_urls")
    val logo: EmployerLogoDto?,
    @SerialName("accredited_it_employer")
    val accreditedIt: Boolean
)