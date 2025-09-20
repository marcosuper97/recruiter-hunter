package com.example.recruiterhunter.data.converters.industries

import com.example.recruiterhunter.data.dto.vacancies.filteres.industry.IndustryGroupDto
import domain.model.filters.Industry

interface IndustriesConverter {
    suspend fun map(industryDto: List<IndustryGroupDto>): List<Industry>
}