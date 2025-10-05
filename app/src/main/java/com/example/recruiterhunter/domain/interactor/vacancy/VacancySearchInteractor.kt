package com.example.recruiterhunter.domain.interactor.vacancy

import domain.model.vacancy.VacanciesList

interface VacancySearchInteractor {
    suspend fun doRequest(query: String, page: Int): Result<VacanciesList>
}