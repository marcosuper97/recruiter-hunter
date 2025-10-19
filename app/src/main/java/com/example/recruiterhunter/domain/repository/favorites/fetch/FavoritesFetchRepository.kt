package com.example.recruiterhunter.domain.repository.favorites.fetch

import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy
import kotlinx.coroutines.flow.Flow

interface FavoritesFetchRepository {
    fun fetchFavorites(): Flow<List<Vacancy>>
}