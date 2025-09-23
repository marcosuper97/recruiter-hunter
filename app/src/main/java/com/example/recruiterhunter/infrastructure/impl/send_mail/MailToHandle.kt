package com.example.recruiterhunter.infrastructure.impl.send_mail

interface MailToHandle {
    suspend fun sendMail()
}