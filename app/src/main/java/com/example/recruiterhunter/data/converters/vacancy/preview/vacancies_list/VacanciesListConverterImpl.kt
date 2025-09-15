package com.example.recruiterhunter.data.converters.vacancy.preview.vacancies_list

import com.example.recruiterhunter.data.dto.vacancies.response.preview.PreviewDto
import com.example.recruiterhunter.data.util.toCurrencySymbol
import domain.model.vacancy.preview.VacancyPreview

class VacanciesListConverterImpl() : VacanciesListConverter {
    override fun map(previewDto: List<PreviewDto>): List<VacancyPreview> =
        previewDto.map { previewDto ->
            VacancyPreview(
                vacancyId = previewDto.id,
                vacancyName = previewDto.name,
                employerName = previewDto.employer.name,
                employerLogo = previewDto.employer.logo?.path ?: "",
                address = previewDto.address?.raw ?: previewDto.area.name,
                salaryFrom = previewDto.salary?.from?.toString() ?: "",
                salaryTo = previewDto.salary?.to?.toString() ?: "",
                currency = previewDto.salary?.currency.toCurrencySymbol()
            )
        }
}