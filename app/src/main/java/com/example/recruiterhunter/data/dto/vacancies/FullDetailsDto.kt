package com.example.recruiterhunter.data.dto.vacancies

import com.example.recruiterhunter.data.dto.vacancies.elements.AddressDto
import com.example.recruiterhunter.data.dto.vacancies.elements.EmployerDto
import com.example.recruiterhunter.data.dto.vacancies.elements.VacancyAreaDto

data class FullDetailsDto(
    override val vacancyName: String,
    override val employer: EmployerDto,
    override val address: AddressDto,
    override val area: VacancyAreaDto
) : VacancyDto