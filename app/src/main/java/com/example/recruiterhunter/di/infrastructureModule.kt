package com.example.recruiterhunter.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.recruiterhunter.domain.actions.send_mail.SendToAction
import com.example.recruiterhunter.domain.actions.settings.SystemSettingsAction
import com.example.recruiterhunter.domain.actions.share.ShareAction
import com.example.recruiterhunter.domain.data_store.ThemeChanger
import com.example.recruiterhunter.domain.services.network_check.NetworkCheckService
import com.example.recruiterhunter.infrastructure.impl.actions.dev_link.SendToActionImpl
import com.example.recruiterhunter.infrastructure.impl.actions.share_vacancy.ShareActionImpl
import com.example.recruiterhunter.infrastructure.impl.actions.system_settings.SystemSettingActionImpl
import com.example.recruiterhunter.infrastructure.impl.services.network_checker.NetworkCheckServiceImpl
import com.example.recruiterhunter.infrastructure.local.datastore.ThemeChangerImpl
import com.example.recruiterhunter.infrastructure.local.roomdb.db.AppDb
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = DataStoreNames.SETTINGS_NAME
)

val infrastructureModule = module {

    single<NetworkCheckService> {
        NetworkCheckServiceImpl(androidContext())
    }

    single<SendToAction> {
        SendToActionImpl(androidContext())
    }

    single<ShareAction> {
        ShareActionImpl(androidContext())
    }

    single<SystemSettingsAction> {
        SystemSettingActionImpl(androidContext())
    }

    single<ThemeChanger> {
        ThemeChangerImpl(androidContext().dataStore)
    }

    single<AppDb> {
        Room.databaseBuilder(androidContext(), AppDb::class.java, "database.db")
            .fallbackToDestructiveMigration(false)
            .build()
    }
}

private object DataStoreNames {
    const val SETTINGS_NAME = "settings"
}