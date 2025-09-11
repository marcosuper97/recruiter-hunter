package domain.repository

interface VacancySearchRepository {
    suspend fun doRequest(query: String, page: Int = 1)
}