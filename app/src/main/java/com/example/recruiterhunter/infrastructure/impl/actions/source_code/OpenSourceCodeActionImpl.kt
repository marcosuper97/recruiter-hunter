package com.example.recruiterhunter.infrastructure.impl.actions.source_code

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import com.example.recruiterhunter.R
import com.example.recruiterhunter.domain.actions.source_code.OpenSourceCodeAction

class OpenSourceCodeActionImpl(private val context: Context) : OpenSourceCodeAction {
    private val appRepositoryUrl = context.getString(R.string.app_repository_url)
    private val gitHubPackage = context.getString(R.string.github_app_package_name)

    override suspend fun openRepository() {
        val githubIntent = Intent(Intent.ACTION_VIEW, appRepositoryUrl.toUri()).apply {
            setPackage("package:$gitHubPackage")
        }

        try {
            context.startActivity(githubIntent)
        } catch (e: Exception) {
            val intent = Intent(Intent.ACTION_VIEW, appRepositoryUrl.toUri()).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            context.startActivity(intent)
        }
    }
}