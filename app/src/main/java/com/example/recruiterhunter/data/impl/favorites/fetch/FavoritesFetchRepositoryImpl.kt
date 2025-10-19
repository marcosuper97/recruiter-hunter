package com.example.recruiterhunter.data.impl.favorites.fetch

import com.example.recruiterhunter.data.converters.vacancy.full.VacanciesDetailsConverter
import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy
import com.example.recruiterhunter.domain.repository.favorites.fetch.FavoritesFetchRepository
import com.example.recruiterhunter.infrastructure.local.roomdb.vacany.dao.VacancyDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesFetchRepositoryImpl(
    private val vacancyDao: VacancyDao,
    private val vacancyDetailsConverter: VacanciesDetailsConverter
) : FavoritesFetchRepository {
    override fun fetchFavorites(): Flow<List<Vacancy>> =
        vacancyDao.getAllVacancies()
            .map { vacancyList ->
                vacancyList
                    .map { vacancy ->
                        vacancyDetailsConverter
                            .map(vacancy)
                    }
            }
}