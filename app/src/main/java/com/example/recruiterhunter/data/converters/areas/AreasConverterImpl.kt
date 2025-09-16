package com.example.recruiterhunter.data.converters.areas

import com.example.recruiterhunter.data.dto.vacancies.filteres.areas.AreaDto
import domain.model.filters.Areas

class AreasConverterImpl() : AreasConverter {
    override suspend fun map(areasDto: List<AreaDto>): List<Areas> = areasDto.map { areasDto ->
        Areas(
            id = areasDto.id,
            parentId = areasDto.parentId,
            name = areasDto.name,
            areas = map(areasDto.areas)
        )
    }
}