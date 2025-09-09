package com.example.recruiterhunter.data.local.vacany.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancies")
data class VacancyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: String,
    val vacancyName: String,
    val employerName: String?,
    val employerLogo: String?,
    val address: String,
    val salaryFrom: Int?,
    val salaryTo: Int?,
    val currency: String?,
    val employmentForm: String?,
    val workFormat: String?,
    val experience: String?,
    val linkUrl: String,
    val description: String,
    val keySkills: String,
    val additionTime: Long
)