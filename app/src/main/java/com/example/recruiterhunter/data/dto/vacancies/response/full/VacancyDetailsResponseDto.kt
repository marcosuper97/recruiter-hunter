package com.example.recruiterhunter.data.dto.vacancies.response.full

import com.example.recruiterhunter.data.dto.vacancies.api.VacancyDto
import com.example.recruiterhunter.data.dto.vacancies.elements.AddressDto
import com.example.recruiterhunter.data.dto.vacancies.elements.EmployerDto
import com.example.recruiterhunter.data.dto.vacancies.elements.EmploymentFormDto
import com.example.recruiterhunter.data.dto.vacancies.elements.ExperienceDto
import com.example.recruiterhunter.data.dto.vacancies.elements.SalaryDto
import com.example.recruiterhunter.data.dto.vacancies.elements.VacancyAreaDto
import com.example.recruiterhunter.data.dto.vacancies.elements.VacancyKeySkillsDto
import com.example.recruiterhunter.data.dto.vacancies.elements.WorkFormatDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VacancyDetailsResponseDto(
    override val id: String,
    override val name: String,
    override val employer: EmployerDto,
    override val address: AddressDto?,
    override val area: VacancyAreaDto,
    @SerialName("salary_range")
    override val salary: SalaryDto?,
    val description: String,
    @SerialName("employment_form")
    val employmentForm: EmploymentFormDto?,
    @SerialName("work_format")
    val workFormat: List<WorkFormatDto>?,
    @SerialName("alternate_url")
    val linkUrl: String,
    val experience: ExperienceDto?,
    @SerialName("key_skills")
    val keySkills: List<VacancyKeySkillsDto>
) : VacancyDto