package com.example.recruiterhunter.data.converters.industries

import com.example.recruiterhunter.data.dto.vacancies.filteres.industry.IndustryDto
import domain.model.filters.Industry

class IndustriesConverterImpl(): IndustriesConverter {
    override suspend fun map(industryDto: List<IndustryDto>): List<Industry> = industryDto.map{
        industryDto ->
        Industry(
            id = industryDto.id,
            name = industryDto.name,
            select = false
        )
    }
}