package domain.repository

import domain.model.vacancy.VacanciesList

interface VacancySearchRepository {
    suspend fun doRequest(query: String, page: Int = 1): Result<VacanciesList>
}