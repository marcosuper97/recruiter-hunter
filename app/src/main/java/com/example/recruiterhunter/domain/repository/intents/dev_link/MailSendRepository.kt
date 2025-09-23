package com.example.recruiterhunter.domain.repository.intents.dev_link

interface MailSendRepository {
    suspend fun mailForDeveloper()
}