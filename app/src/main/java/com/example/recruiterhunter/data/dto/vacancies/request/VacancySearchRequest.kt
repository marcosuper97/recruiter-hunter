package com.example.recruiterhunter.data.dto.vacancies.request

data class VacancySearchRequest(
    val page: Int = 1,
    val perPage: String = "20",
    val text: String,
    val area: String? = null,
    val industry: String? = null,
    val salary: String? = null,
    val onlyWithSalary: Boolean = false,
)
