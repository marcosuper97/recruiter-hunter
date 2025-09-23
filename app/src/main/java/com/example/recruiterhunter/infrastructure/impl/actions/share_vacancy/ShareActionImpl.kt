package com.example.recruiterhunter.infrastructure.impl.actions.share_vacancy

import android.content.Context
import android.content.Intent
import com.example.recruiterhunter.domain.actions.share.ShareAction

class ShareActionImpl(private val context: Context) : ShareAction {
    override suspend fun shareVacancy(urlLink: String) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, urlLink)
        }
        context.startActivity(Intent.createChooser(shareIntent, "Поделиться ссылкой"))
    }
}

