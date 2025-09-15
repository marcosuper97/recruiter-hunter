package domain.impl

import domain.interactor.VacancySearchInteractor
import domain.model.vacancy.VacanciesList
import domain.repository.VacancySearchRepository

class VacancySearchInteractorImpl(private val vacancySearchRepository: VacancySearchRepository) :
    VacancySearchInteractor {
    override suspend fun doRequest(
        query: String,
        page: Int
    ): Result<VacanciesList> = vacancySearchRepository.doRequest(query, page)
}