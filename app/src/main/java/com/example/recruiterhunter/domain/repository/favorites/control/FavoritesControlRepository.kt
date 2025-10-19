package com.example.recruiterhunter.domain.repository.favorites.control

import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy

interface FavoritesControlRepository {
    suspend fun removeFromFavorites(id: Long)
    suspend fun addToFavorites(vacancy: Vacancy)
}