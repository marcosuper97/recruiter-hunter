package com.example.recruiterhunter.infrastructure.impl.actions.dev_link

import android.content.Context
import android.content.Intent
import com.example.recruiterhunter.domain.actions.send_mail.SendToAction

class SendToActionImpl(private val context: Context) : SendToAction {
    override suspend fun sendMail() {
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_EMAIL, arrayOf("recipient@example.com"))
            putExtra(Intent.EXTRA_SUBJECT, "Введите тему письма")
            putExtra(Intent.EXTRA_TEXT, "Текст письма")
        }
        context.startActivity(Intent.createChooser(emailIntent, "Выберите способ отправки"))
    }
}