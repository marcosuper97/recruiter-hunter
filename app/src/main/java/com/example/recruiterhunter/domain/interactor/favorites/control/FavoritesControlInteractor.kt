package com.example.recruiterhunter.domain.interactor.favorites.control

import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy

interface FavoritesControlInteractor {
    suspend fun removeFromFavorites(id: Long)
    suspend fun addToFavorites(vacancy: Vacancy)
}