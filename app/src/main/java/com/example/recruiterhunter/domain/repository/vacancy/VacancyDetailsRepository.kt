package domain.repository

import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy

interface VacancyDetailsRepository {
    suspend fun fetchDetails(vacancyId: Long): Result<Vacancy>
}