package com.example.recruiterhunter.infrastructure.impl.actions.system_settings

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.core.net.toUri
import com.example.recruiterhunter.domain.actions.settings.SystemSettingsAction

class SystemSettingActionImpl(private val context: Context) : SystemSettingsAction {
    override suspend fun openSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = context.packageName.toUri()
        }
        context.startActivity(intent)
    }
}