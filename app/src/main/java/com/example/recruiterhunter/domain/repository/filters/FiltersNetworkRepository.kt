package com.example.recruiterhunter.domain.repository.filters

import domain.model.filters.Areas
import domain.model.filters.Industry

interface FiltersNetworkRepository {
    suspend fun getAreas(): Areas
    suspend fun getIndustries(): Industry
}