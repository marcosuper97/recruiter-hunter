package com.example.recruiterhunter.domain.actions.send_mail

interface SendToAction {
    suspend fun sendMail()
}