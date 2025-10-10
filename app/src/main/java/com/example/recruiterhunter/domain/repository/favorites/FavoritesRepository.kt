package com.example.recruiterhunter.domain.repository.favorites

import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    fun fetchFavorites(): Flow<List<Vacancy>>
    suspend fun removeFromFavorites(id: Long)
    suspend fun addToFavorites(vacancy: Vacancy)
}