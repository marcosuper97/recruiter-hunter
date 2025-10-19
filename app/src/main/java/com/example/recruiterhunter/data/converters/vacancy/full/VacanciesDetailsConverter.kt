package com.example.recruiterhunter.data.converters.vacancy.full

import com.example.recruiterhunter.data.dto.vacancies.response.details.VacancyDetailsResponseDto
import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy
import com.example.recruiterhunter.infrastructure.local.roomdb.vacany.entity.VacancyEntity

interface VacanciesDetailsConverter {
    fun map(dto: VacancyDetailsResponseDto): Vacancy
    fun mapToEntity(vacancy: Vacancy): VacancyEntity
    fun map(vacancyEntity: VacancyEntity): Vacancy
}