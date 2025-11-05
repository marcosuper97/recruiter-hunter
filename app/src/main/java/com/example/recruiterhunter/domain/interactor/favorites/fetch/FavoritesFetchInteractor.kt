package com.example.recruiterhunter.domain.interactor.favorites.fetch

import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy
import kotlinx.coroutines.flow.Flow

interface FavoritesFetchInteractor {
    fun fetchFavorites(): Flow<List<Vacancy>>
}