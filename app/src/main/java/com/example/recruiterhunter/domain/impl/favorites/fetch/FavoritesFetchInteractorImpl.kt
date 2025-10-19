package com.example.recruiterhunter.domain.impl.favorites.fetch

import com.example.recruiterhunter.domain.interactor.favorites.fetch.FavoritesFetchInteractor
import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy
import com.example.recruiterhunter.domain.repository.favorites.fetch.FavoritesFetchRepository
import kotlinx.coroutines.flow.Flow

class FavoritesFetchInteractorImpl(
    private val favoritesRepository: FavoritesFetchRepository
) :
    FavoritesFetchInteractor {
    override fun fetchFavorites(): Flow<List<Vacancy>> = favoritesRepository.fetchFavorites()
}