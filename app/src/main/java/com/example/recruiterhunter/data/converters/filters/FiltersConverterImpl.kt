package com.example.recruiterhunter.data.converters.filters

import com.example.recruiterhunter.data.local.filters.entity.FiltersEntity
import domain.model.filters.Filters

class FiltersConverterImpl() : FiltersConverter {
    override suspend fun map(filtersEntity: FiltersEntity): Filters = Filters(
        country = filtersEntity.country,
        countryId = filtersEntity.countryId,
        area = filtersEntity.area,
        areaId = filtersEntity.areaId,
        industry = filtersEntity.industry,
        industryId = filtersEntity.industryId,
        salary = filtersEntity.salary,
        onlyWithSalary = filtersEntity.onlyWithSalary
    )

    override suspend fun map(filters: Filters): FiltersEntity = FiltersEntity(
        country = filters.country,
        countryId = filters.countryId,
        area = filters.area,
        areaId = filters.areaId,
        industry = filters.industry,
        industryId = filters.industryId,
        salary = filters.salary,
        onlyWithSalary = filters.onlyWithSalary
    )
}