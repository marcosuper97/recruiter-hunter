package com.example.recruiterhunter.data.converters.vacancy.preview.vacancies_list

import com.example.recruiterhunter.data.dto.vacancies.response.preview.PreviewDto
import com.example.recruiterhunter.domain.model.vacancy.preview.VacancyPreview
import com.example.recruiterhunter.infrastructure.converter.salary.VacancySalaryConverter

class VacanciesListConverterImpl(private val salaryConverter: VacancySalaryConverter) :
    VacanciesListConverter {
    override fun map(previewDto: List<PreviewDto>): List<VacancyPreview> =
        previewDto.map { previewDto ->
            VacancyPreview(
                vacancyId = previewDto.id,
                vacancyName = previewDto.name,
                employerName = previewDto.employer.name,
                employerLogo = previewDto.employer.logo?.path ?: "",
                salary = salaryConverter.map(previewDto.salary),
                address = previewDto.address?.raw ?: previewDto.area.name,
            )
        }
}