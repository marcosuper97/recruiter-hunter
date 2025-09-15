package com.example.recruiterhunter.data.converters.areas

import com.example.recruiterhunter.data.dto.vacancies.filteres.areas.AreaDto
import domain.model.filters.Areas

interface AreasConverter {
    suspend fun map(areasDto: AreaDto): Areas
}