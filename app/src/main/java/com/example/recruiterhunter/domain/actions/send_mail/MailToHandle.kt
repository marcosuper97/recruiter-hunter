package com.example.recruiterhunter.domain.actions.send_mail

interface MailToHandle {
    suspend fun sendMail()
}