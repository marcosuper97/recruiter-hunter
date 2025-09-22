package com.example.recruiterhunter.domain.model.vacancy.preview

data class VacancyPreview(
    val vacancyId: Long,
    val vacancyName: String,
    val employerName: String,
    val employerLogo: String,
    val address: String,
    val salaryFrom: String,
    val salaryTo: String,
    val currency: String
)
