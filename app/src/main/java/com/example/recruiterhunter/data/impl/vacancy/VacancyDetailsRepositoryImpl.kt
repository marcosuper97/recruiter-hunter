package com.example.recruiterhunter.data.impl.vacancy

import com.example.recruiterhunter.data.converters.vacancy.full.VacanciesDetailsConverter
import com.example.recruiterhunter.data.impl.network.vacancies.HhNetworkClient
import com.example.recruiterhunter.infrastructure.local.roomdb.vacany.dao.VacancyDao
import domain.model.vacancy.details.Vacancy
import domain.repository.VacancyDetailsRepository

class VacancyDetailsRepositoryImpl(
    private val hhNetworkClient: HhNetworkClient,
    private val vacanciesDetailsConverter: VacanciesDetailsConverter,
    private val vacancyDao: VacancyDao
) : VacancyDetailsRepository {
    override suspend fun fetchDetails(vacancyId: Long): Result<Vacancy> =
        vacancyDao.isFavorite(vacancyId)
            .let { isFavorite ->
                if (isFavorite) {
                    Result.success(vacanciesDetailsConverter.map(vacancyDao.getVacancy(vacancyId)!!))
                } else {
                    hhNetworkClient.detailsVacancyRequest(vacancyId)
                        .map { vacanciesDetailsConverter.map(it) }
                }
            }
}