package com.example.recruiterhunter.domain.model.vacancy.details

import androidx.compose.runtime.Immutable

@Immutable
data class Vacancy(
    val vacancyId: Long,
    val vacancyName: String,
    val employerName: String,
    val employerLogo: String,
    val address: String,
    val salaryFrom: String,
    val salaryTo: String,
    val currency: String,
    val employmentForm: String,
    val workFormat: List<String>,
    val experience: String,
    val linkUrl: String,
    val description: String,
    val keySkills: List<String>,
    val isFavorite: Boolean = false
)
