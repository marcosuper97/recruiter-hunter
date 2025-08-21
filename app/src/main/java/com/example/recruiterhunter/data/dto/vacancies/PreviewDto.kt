package com.example.recruiterhunter.data.dto.vacancies

import com.example.recruiterhunter.data.dto.vacancies.elements.AddressDto
import com.example.recruiterhunter.data.dto.vacancies.elements.EmployerDto
import com.example.recruiterhunter.data.dto.vacancies.elements.VacancyAreaDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreviewDto(
    @SerialName("name")
    override val vacancyName: String,
    override val employer: EmployerDto,
    override val address: AddressDto,
    override val area: VacancyAreaDto
) : VacancyDto