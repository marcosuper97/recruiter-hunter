package com.example.recruiterhunter.domain.impl.favorites

import com.example.recruiterhunter.domain.interactor.favorites.FavoritesInteractor
import com.example.recruiterhunter.domain.repository.favorites.FavoritesRepository
import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy
import kotlinx.coroutines.flow.Flow

class FavoritesInteractorImpl(
    private val favoritesRepository: FavoritesRepository
) :
    FavoritesInteractor {
    override fun fetchFavorites(): Flow<List<Vacancy>> = favoritesRepository.fetchFavorites()

    override suspend fun removeFromFavorites(id: Long) {
        favoritesRepository.removeFromFavorites(id)
    }

    override suspend fun addToFavorites(vacancy: Vacancy) {
        favoritesRepository.addToFavorites(vacancy)
    }
}