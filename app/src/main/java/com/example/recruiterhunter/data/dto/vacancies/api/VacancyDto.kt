package com.example.recruiterhunter.data.dto.vacancies.api

import com.example.recruiterhunter.data.dto.vacancies.elements.AddressDto
import com.example.recruiterhunter.data.dto.vacancies.elements.EmployerDto
import com.example.recruiterhunter.data.dto.vacancies.elements.SalaryDto
import com.example.recruiterhunter.data.dto.vacancies.elements.VacancyAreaDto

interface VacancyDto {
    val id: Long
    val name: String
    val employer: EmployerDto
    val address: AddressDto?
    val area: VacancyAreaDto
    val salary: SalaryDto?
}