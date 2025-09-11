package com.example.recruiterhunter.data.converters.vacancy.preview

import com.example.recruiterhunter.data.dto.vacancies.response.preview.VacanciesResponseDto
import com.example.recruiterhunter.data.local.vacany.entity.VacancyEntity
import domain.model.vacancy.VacanciesList
import domain.model.vacancy.full.VacancyFull

interface VacanciesPreviewConverter {
    suspend fun map(vacanciesResponseDto: VacanciesResponseDto): VacanciesList
}