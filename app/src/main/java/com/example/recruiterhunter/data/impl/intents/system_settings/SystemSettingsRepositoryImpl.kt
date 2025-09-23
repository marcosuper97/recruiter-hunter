package com.example.recruiterhunter.data.impl.intents.system_settings

import android.content.Context
import com.example.recruiterhunter.domain.repository.intents.system_settings.SystemSettingsRepository

class SystemSettingsRepositoryImpl(private val context: Context) : SystemSettingsRepository {
    override suspend fun openSystemSettings() {}
}