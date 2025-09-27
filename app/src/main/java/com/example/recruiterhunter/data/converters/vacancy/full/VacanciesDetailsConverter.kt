package com.example.recruiterhunter.data.converters.vacancy.full

import com.example.recruiterhunter.data.dto.vacancies.response.details.VacancyDetailsResponseDto
import com.example.recruiterhunter.infrastructure.local.roomdb.vacany.entity.VacancyEntity
import domain.model.vacancy.details.Vacancy

interface VacanciesDetailsConverter {
    fun map(dto: VacancyDetailsResponseDto): Vacancy
    fun mapToEntity(vacancy: Vacancy): VacancyEntity
    fun map(vacancyEntity: VacancyEntity): Vacancy
}