package com.example.recruiterhunter.data.converters.vacancy.preview.vacancies_list

import com.example.recruiterhunter.data.dto.vacancies.response.preview.PreviewDto
import com.example.recruiterhunter.domain.model.vacancy.preview.VacancyPreview

interface VacanciesListConverter {
    fun map(previewDto: List<PreviewDto>): List<VacancyPreview>
}