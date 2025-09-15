package com.example.recruiterhunter.data.impl.filters

import com.example.recruiterhunter.data.network.vacancies.HhNetworkClient
import com.example.recruiterhunter.domain.repository.filters.FiltersNetworkRepository
import domain.model.filters.Areas
import domain.model.filters.Industry

class FiltersNetworkRepositoryImpl(
    private val hhNetworkClient: HhNetworkClient
) : FiltersNetworkRepository {
    override suspend fun getAreas(): Areas {
        TODO("Not yet implemented")
    }

    override suspend fun getIndustries(): Industry {
        TODO("Not yet implemented")
    }

}