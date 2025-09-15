package com.example.recruiterhunter.data.converters.industries

import com.example.recruiterhunter.data.dto.vacancies.filteres.industry.IndustryDto
import domain.model.filters.Industry

class IndustriesConverterImpl(): IndustriesConverter {
    override suspend fun map(industryDto: IndustryDto): Industry = Industry(
        id = TODO(),
        name = TODO()
    )
}