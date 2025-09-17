package com.example.recruiterhunter.domain.interactor.favorites

import domain.model.vacancy.details.Vacancy
import kotlinx.coroutines.flow.Flow

interface FavoritesInteractor {
    fun fetchFavorites(): Flow<List<Vacancy>>
    suspend fun removeFromFavorites(id: Long)
    suspend fun addToFavorites(vacancy: Vacancy)
}