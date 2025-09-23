package com.example.recruiterhunter.domain.repository.intents.share_vacancy

interface VacancyShareRepository {
    suspend fun shareVacancy(urlLink: String)
}