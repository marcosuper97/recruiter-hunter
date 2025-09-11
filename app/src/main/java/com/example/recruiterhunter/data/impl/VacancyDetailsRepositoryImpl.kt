package com.example.recruiterhunter.data.impl

import domain.model.vacancy.full.VacancyFull
import domain.repository.VacancyDetailsRepository

class VacancyDetailsRepositoryImpl(): VacancyDetailsRepository {
    override suspend fun fetchDetails(vacancyId: Int): Result<VacancyFull> {
        TODO("Not yet implemented")
    }
}