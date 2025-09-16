package com.example.recruiterhunter.data.converters.areas

import com.example.recruiterhunter.data.dto.vacancies.filteres.areas.AreaDto
import domain.model.filters.Areas

class AreasConverterImpl() : AreasConverter {
    override suspend fun map(areasDto: List<AreaDto>): List<Areas> =
        areasDto.map { (id, parentId, name, areas) ->
            Areas(
                id = id,
                parentId = parentId,
                name = name,
                areas = map(areas)
            )
        }
}