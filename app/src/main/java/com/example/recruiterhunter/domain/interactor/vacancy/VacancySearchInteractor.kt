package domain.interactor

import domain.model.vacancy.VacanciesList

interface VacancySearchInteractor {
    suspend fun doRequest(query: String, page: Int): Result<VacanciesList>
}