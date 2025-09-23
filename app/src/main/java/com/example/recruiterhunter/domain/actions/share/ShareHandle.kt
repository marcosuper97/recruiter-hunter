package com.example.recruiterhunter.domain.actions.share

interface ShareHandle {
    suspend fun shareVacancy(urlLink: String)
}