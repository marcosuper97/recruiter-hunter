package com.example.recruiterhunter.data.converters.vacancy.preview

import com.example.recruiterhunter.data.converters.vacancy.preview.vacancies_list.VacanciesListConverter
import com.example.recruiterhunter.data.dto.vacancies.response.preview.VacanciesResponseDto
import domain.model.vacancy.VacanciesList

class VacanciesPreviewConverterImpl(private val listConverter: VacanciesListConverter) :
    VacanciesPreviewConverter {
    override suspend fun map(vacanciesResponseDto: VacanciesResponseDto): VacanciesList {
        return VacanciesList(
            page = vacanciesResponseDto.page,
            pages = vacanciesResponseDto.pages,
            found = vacanciesResponseDto.found,
            vacanciesList = listConverter.map(vacanciesResponseDto.vacancies)
        )
    }
}