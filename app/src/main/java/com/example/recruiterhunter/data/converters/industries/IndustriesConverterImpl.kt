package com.example.recruiterhunter.data.converters.industries

import com.example.recruiterhunter.data.dto.vacancies.filteres.industry.IndustryGroupDto
import domain.model.filters.Industry

class IndustriesConverterImpl() : IndustriesConverter {
    override suspend fun map(industryDto: List<IndustryGroupDto>): List<Industry> =
        industryDto.flatMap { groupDto ->
            groupDto.industries.map { industryDto ->
                Industry(
                    id = industryDto.id,
                    name = industryDto.name,
                    select = false
                )
            }
        }
}