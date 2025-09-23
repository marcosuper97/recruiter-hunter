package com.example.recruiterhunter.domain.actions.share

interface ShareAction {
    suspend fun shareVacancy(urlLink: String)
}