package com.example.recruiterhunter.data.impl.filters

import com.example.recruiterhunter.data.converters.areas.AreasConverter
import com.example.recruiterhunter.data.converters.industries.IndustriesConverter
import com.example.recruiterhunter.data.impl.network.vacancies.HhNetworkClient
import com.example.recruiterhunter.domain.repository.filters.FiltersNetworkRepository
import domain.model.filters.Areas
import domain.model.filters.Industry

class FiltersNetworkRepositoryImpl(
    private val hhNetworkClient: HhNetworkClient,
    private val areasConverter: AreasConverter,
    private val industriesConverter: IndustriesConverter
) : FiltersNetworkRepository {
    private var cachedAreas: Result<List<Areas>>? = null
    private var cachedIndustries: Result<List<Industry>>? = null

    override suspend fun getAreas(): Result<List<Areas>> =
        cachedAreas ?: hhNetworkClient.getAreas().map { listAreasDto ->
            areasConverter.map(listAreasDto)
        }.also { result ->
            cachedAreas = result
        }

    override suspend fun getIndustries(): Result<List<Industry>> =
        cachedIndustries ?: hhNetworkClient.getIndustries().map { industryGroupDto ->
            industriesConverter.map(industryGroupDto)
        }.also { result ->
            cachedIndustries = result
        }
}