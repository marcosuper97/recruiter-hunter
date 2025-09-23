package com.example.recruiterhunter.infrastructure.impl.share

interface ShareHandle {
    suspend fun shareVacancy(urlLink: String)
}