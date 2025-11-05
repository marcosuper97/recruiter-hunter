package com.example.recruiterhunter.domain.impl.favorites.control

import com.example.recruiterhunter.domain.interactor.favorites.control.FavoritesControlInteractor
import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy
import com.example.recruiterhunter.domain.repository.favorites.control.FavoritesControlRepository

class FavoritesControlInteractorImpl(
    private val favoritesRepository: FavoritesControlRepository
) :
    FavoritesControlInteractor {
    override suspend fun removeFromFavorites(id: Long) {
        favoritesRepository.removeFromFavorites(id)
    }

    override suspend fun addToFavorites(vacancy: Vacancy) {
        favoritesRepository.addToFavorites(vacancy)
    }
}