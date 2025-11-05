package com.example.recruiterhunter.infrastructure.converter.salary

import com.example.recruiterhunter.data.dto.vacancies.elements.SalaryDto

interface VacancySalaryConverter {
    fun map(salary: SalaryDto?): String
}