package domain.repository

import domain.model.vacancy.full.VacancyFull

interface VacancyDetailsRepository {
    suspend fun fetchDetails(vacancyId: Int): Result<VacancyFull>
}