package com.example.recruiterhunter.data.impl.favorites.control

import com.example.recruiterhunter.data.converters.vacancy.full.VacanciesDetailsConverter
import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy
import com.example.recruiterhunter.domain.repository.favorites.control.FavoritesControlRepository
import com.example.recruiterhunter.infrastructure.local.roomdb.vacany.dao.VacancyDao

class FavoritesControlRepositoryImpl(
    private val vacancyDao: VacancyDao,
    private val vacancyDetailsConverter: VacanciesDetailsConverter
) : FavoritesControlRepository {
    override suspend fun removeFromFavorites(id: Long) {
        vacancyDao.deleteVacancy(id)
    }

    override suspend fun addToFavorites(vacancy: Vacancy) {
        vacancyDao.insertVacancy(vacancyDetailsConverter.mapToEntity(vacancy))
    }
}