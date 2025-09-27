package com.example.recruiterhunter.infrastructure.impl.actions.dev_link

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.net.toUri
import com.example.recruiterhunter.domain.actions.send_mail.SendToAction

class SendToActionImpl(private val context: Context) : SendToAction {
    override suspend fun sendMail() {
        val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
            data =
                "mailto:gorpishin97@yandex.ru?subject=${Uri.encode("Введите тему письма")}&body=${
                    Uri.encode("Текст письма")
                }".toUri()
        }

        if (emailIntent.resolveActivity(context.packageManager) != null) {
            val chooserIntent =
                Intent.createChooser(emailIntent, "Выберите способ отправки").apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }
            context.startActivity(chooserIntent)
        } else {
            sendMailWithActionSend()
        }
    }

    private fun sendMailWithActionSend() {
        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "message/rfc822" // MIME type для email
            putExtra(Intent.EXTRA_EMAIL, arrayOf("gorpishin97@yandex.ru"))
            putExtra(Intent.EXTRA_SUBJECT, "Введите тему письма")
            putExtra(Intent.EXTRA_TEXT, "Текст письма")
        }.apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        context.startActivity(emailIntent)
    }
}
