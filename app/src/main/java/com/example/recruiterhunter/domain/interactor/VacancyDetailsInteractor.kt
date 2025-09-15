package com.example.recruiterhunter.domain.interactor

import domain.model.vacancy.details.Vacancy

interface VacancyDetailsInteractor {
    suspend fun fetchDetails(id:Long): Result<Vacancy>
}