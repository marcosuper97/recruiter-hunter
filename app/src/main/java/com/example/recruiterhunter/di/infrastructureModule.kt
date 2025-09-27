package com.example.recruiterhunter.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.recruiterhunter.domain.actions.send_mail.SendToAction
import com.example.recruiterhunter.domain.actions.settings.SystemSettingsAction
import com.example.recruiterhunter.domain.actions.share.ShareAction
import com.example.recruiterhunter.domain.actions.source_code.OpenSourceCodeAction
import com.example.recruiterhunter.domain.services.application_theme.ThemeChangerService
import com.example.recruiterhunter.domain.services.application_theme.ThemeGetterService
import com.example.recruiterhunter.domain.services.network_check.NetworkCheckService
import com.example.recruiterhunter.infrastructure.impl.actions.dev_link.SendToActionImpl
import com.example.recruiterhunter.infrastructure.impl.actions.share_vacancy.ShareActionImpl
import com.example.recruiterhunter.infrastructure.impl.actions.source_code.OpenSourceCodeActionImpl
import com.example.recruiterhunter.infrastructure.impl.actions.system_settings.SystemSettingActionImpl
import com.example.recruiterhunter.infrastructure.impl.services.network_checker.NetworkCheckServiceImpl
import com.example.recruiterhunter.infrastructure.local.datastore.application_theme.ThemeChangerServiceImpl
import com.example.recruiterhunter.infrastructure.local.datastore.application_theme.ThemeGetterServiceImpl
import com.example.recruiterhunter.infrastructure.local.roomdb.db.AppDb
import com.example.recruiterhunter.infrastructure.local.roomdb.filters.dao.FiltersDao
import com.example.recruiterhunter.infrastructure.local.roomdb.vacany.dao.VacancyDao
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

    single<ThemeChangerService> {
        ThemeChangerServiceImpl(androidContext().dataStore)
    }

    single<ThemeGetterService> {
        ThemeGetterServiceImpl(androidContext().dataStore)
    }

    single<OpenSourceCodeAction> {
        OpenSourceCodeActionImpl(androidContext())
    }

    single<AppDb> {
        Room.databaseBuilder(androidContext(), AppDb::class.java, "database.db")
            .fallbackToDestructiveMigration(false)
            .build()
    }

    single<FiltersDao> { get<AppDb>().filtersDao() }

    single<VacancyDao> { get<AppDb>().vacanciesDao() }
}

private object DataStoreNames {
    const val SETTINGS_NAME = "settings"
}