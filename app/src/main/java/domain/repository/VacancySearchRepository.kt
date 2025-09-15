package domain.repository

import domain.model.vacancy.VacanciesList
import domain.model.vacancy.preview.VacancyPreview

interface VacancySearchRepository {
    suspend fun doRequest(query: String, page: Int = 1): Result<VacanciesList>
}