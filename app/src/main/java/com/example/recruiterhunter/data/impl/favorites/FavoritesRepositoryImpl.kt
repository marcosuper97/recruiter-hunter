package com.example.recruiterhunter.data.impl.favorites

import com.example.recruiterhunter.data.converters.vacancy.full.VacanciesDetailsConverter
import com.example.recruiterhunter.domain.repository.favorites.FavoritesRepository
import com.example.recruiterhunter.infrastructure.local.roomdb.vacany.dao.VacancyDao
import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoritesRepositoryImpl(
    private val vacancyDao: VacancyDao,
    private val vacancyDetailsConverter: VacanciesDetailsConverter
) : FavoritesRepository {
    override fun fetchFavorites(): Flow<List<Vacancy>> =
        vacancyDao.getAllVacancies()
            .map { vacancyList ->
                vacancyList
                    .map { vacancy ->
                        vacancyDetailsConverter
                            .map(vacancy)
                    }
            }

    override suspend fun removeFromFavorites(id: Long) {
        vacancyDao.deleteVacancy(id)
    }

    override suspend fun addToFavorites(vacancy: Vacancy) {
        vacancyDao.insertVacancy(vacancyDetailsConverter.mapToEntity(vacancy))
    }
}