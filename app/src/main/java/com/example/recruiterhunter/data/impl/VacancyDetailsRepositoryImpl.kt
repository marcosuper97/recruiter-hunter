package com.example.recruiterhunter.data.impl

import domain.model.vacancy.details.Vacancy
import domain.repository.VacancyDetailsRepository

class VacancyDetailsRepositoryImpl() : VacancyDetailsRepository {
    override suspend fun fetchDetails(vacancyId: Int): Result<Vacancy> {
        TODO("Not yet implemented")
    }
}