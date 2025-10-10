package com.example.recruiterhunter.domain.impl.vacancy

import com.example.recruiterhunter.domain.interactor.vacancy.VacancyDetailsInteractor
import com.example.recruiterhunter.domain.model.vacancy.details.Vacancy
import domain.repository.VacancyDetailsRepository

class VacancyDetailsInteractorImpl(private val vacancyDetailsRepository: VacancyDetailsRepository) :
    VacancyDetailsInteractor {
    override suspend fun fetchDetails(id: Long): Result<Vacancy> =
        vacancyDetailsRepository.fetchDetails(id)
}