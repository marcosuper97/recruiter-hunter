package domain.repository

import domain.model.vacancy.details.Vacancy

interface VacancyDetailsRepository {
    suspend fun fetchDetails(vacancyId: Int): Result<Vacancy>
}