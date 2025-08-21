package com.example.recruiterhunter.data.dto.vacancies

import com.example.recruiterhunter.data.dto.vacancies.elements.AddressDto
import com.example.recruiterhunter.data.dto.vacancies.elements.EmployerDto
import com.example.recruiterhunter.data.dto.vacancies.elements.VacancyAreaDto

interface VacancyDto {
    val vacancyName: String
    val employer: EmployerDto
    val address: AddressDto
    val area: VacancyAreaDto
}