package com.example.recruiterhunter.data.converters.vacancy.preview

import com.example.recruiterhunter.data.dto.vacancies.response.preview.VacanciesResponseDto
import domain.model.vacancy.VacanciesList

class VacanciesPreviewConverterImpl(): VacanciesPreviewConverter {
    override suspend fun map(vacanciesResponseDto: VacanciesResponseDto): VacanciesList {
        TODO("Not yet implemented")
    }
}