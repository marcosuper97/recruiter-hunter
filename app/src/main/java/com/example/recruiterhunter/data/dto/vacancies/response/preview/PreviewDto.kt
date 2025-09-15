package com.example.recruiterhunter.data.dto.vacancies.response.preview

import com.example.recruiterhunter.data.dto.vacancies.api.VacancyDto
import com.example.recruiterhunter.data.dto.vacancies.elements.AddressDto
import com.example.recruiterhunter.data.dto.vacancies.elements.EmployerDto
import com.example.recruiterhunter.data.dto.vacancies.elements.SalaryDto
import com.example.recruiterhunter.data.dto.vacancies.elements.VacancyAreaDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PreviewDto(
    override val id: Long,
    override val name: String,
    override val employer: EmployerDto,
    override val address: AddressDto?,
    override val area: VacancyAreaDto,
    @SerialName("salary_range")
    override val salary: SalaryDto?
) : VacancyDto