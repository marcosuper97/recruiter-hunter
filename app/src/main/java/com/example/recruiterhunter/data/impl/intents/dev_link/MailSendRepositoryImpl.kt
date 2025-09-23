package com.example.recruiterhunter.data.impl.intents.dev_link

import com.example.recruiterhunter.domain.repository.intents.dev_link.MailSendRepository

class MailSendRepositoryImpl() : MailSendRepository {
    override suspend fun mailForDeveloper() {}
}