package com.example.recruiterhunter.data.converters.vacancy.full

import com.example.recruiterhunter.data.dto.vacancies.api.VacancyDto
import com.example.recruiterhunter.data.local.vacany.entity.VacancyEntity
import domain.model.vacancy.full.VacancyFull

interface VacanciesDetailsConverter {
    fun map(dto: VacancyDto): VacancyFull
    fun mapToEntity(vacancy: VacancyFull): VacancyEntity
}