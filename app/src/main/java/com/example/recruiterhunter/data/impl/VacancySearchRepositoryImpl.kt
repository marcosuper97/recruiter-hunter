package com.example.recruiterhunter.data.impl

import domain.repository.VacancySearchRepository

class VacancySearchRepositoryImpl(): VacancySearchRepository {
    override suspend fun doRequest(query: String, page: Int) {
        TODO("Not yet implemented")
    }
}