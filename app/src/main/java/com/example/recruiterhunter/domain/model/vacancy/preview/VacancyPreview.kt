package com.example.recruiterhunter.domain.model.vacancy.preview

import androidx.compose.runtime.Immutable


@Immutable
data class VacancyPreview(
    val vacancyId: Long,
    val vacancyName: String,
    val employerName: String,
    val employerLogo: String,
    val address: String,
    val salary: String
)
