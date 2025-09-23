package com.example.recruiterhunter.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.example.recruiterhunter.data.converters.areas.AreasConverter
import com.example.recruiterhunter.data.converters.areas.AreasConverterImpl
import com.example.recruiterhunter.data.converters.filters.FiltersConverter
import com.example.recruiterhunter.data.converters.filters.FiltersConverterImpl
import com.example.recruiterhunter.data.converters.industries.IndustriesConverter
import com.example.recruiterhunter.data.converters.industries.IndustriesConverterImpl
import com.example.recruiterhunter.data.converters.sce.full_response.CseResponseConverter
import com.example.recruiterhunter.data.converters.sce.full_response.CseResponseConverterImpl
import com.example.recruiterhunter.data.converters.sce.item.CseItemResponseConverter
import com.example.recruiterhunter.data.converters.sce.item.CseItemResponseConverterImpl
import com.example.recruiterhunter.data.converters.vacancy.full.VacanciesDetailsConverter
import com.example.recruiterhunter.data.converters.vacancy.full.VacanciesDetailsConverterImpl
import com.example.recruiterhunter.data.converters.vacancy.preview.VacanciesPreviewConverter
import com.example.recruiterhunter.data.converters.vacancy.preview.VacanciesPreviewConverterImpl
import com.example.recruiterhunter.data.converters.vacancy.preview.vacancies_list.VacanciesListConverter
import com.example.recruiterhunter.data.converters.vacancy.preview.vacancies_list.VacanciesListConverterImpl
import com.example.recruiterhunter.data.impl.network.google_cse.api.GoogleCseApi
import com.example.recruiterhunter.data.impl.network.request_engine.RequestEngineImpl
import com.example.recruiterhunter.data.impl.network.vacancies.HhNetworkClient
import com.example.recruiterhunter.data.impl.network.vacancies.HhRetrofitClient
import com.example.recruiterhunter.data.impl.network.vacancies.api.HhApi
import com.example.recruiterhunter.data.local.data_store.ThemeDataStore
import com.example.recruiterhunter.data.local.data_store.impl.ThemeDataStoreImpl
import com.example.recruiterhunter.data.local.roomdb.db.AppDb
import com.example.recruiterhunter.data.local.roomdb.filters.dao.FiltersDao
import com.example.recruiterhunter.data.local.roomdb.vacany.dao.VacancyDao
import com.example.recruiterhunter.domain.services.network_check.NetworkCheckService
import com.example.recruiterhunter.domain.services.request_engine.RequestEngine
import com.example.recruiterhunter.infrastructure.impl.services.network_checker.NetworkCheckServiceImpl
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private val json = Json {
    ignoreUnknownKeys = true
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = DataStoreNames.SETTINGS_NAME
)

val dataModule = module {
    single<HhApi> {
        Retrofit.Builder()
            .baseUrl(ApiConfig.HH_BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(HhApi::class.java)

    }

    single<HhNetworkClient> {
        HhRetrofitClient(get(), get())
    }

    single<GoogleCseApi> {
        Retrofit.Builder()
            .baseUrl(ApiConfig.CSE_BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(GoogleCseApi::class.java)

    }

    single<AppDb> {
        Room.databaseBuilder(androidContext(), AppDb::class.java, "database.db")
            .fallbackToDestructiveMigration(false)
            .build()
    }

    single<FiltersDao> { get<AppDb>().filtersDao() }

    single<VacancyDao> { get<AppDb>().vacanciesDao() }

    single<VacanciesListConverter> {
        VacanciesListConverterImpl()
    }

    single<VacanciesPreviewConverter> {
        VacanciesPreviewConverterImpl(get())
    }

    single<VacanciesDetailsConverter> {
        VacanciesDetailsConverterImpl()
    }

    single<FiltersConverter> {
        FiltersConverterImpl()
    }

    single<AreasConverter> {
        AreasConverterImpl()
    }

    single<IndustriesConverter> {
        IndustriesConverterImpl()
    }

    single<CseItemResponseConverter> {
        CseItemResponseConverterImpl()
    }

    single<CseResponseConverter> {
        CseResponseConverterImpl(get())
    }

    single<NetworkCheckService> {
        NetworkCheckServiceImpl(get())
    }

    single<RequestEngine> {
        RequestEngineImpl(get())
    }

    single<ThemeDataStore> {
        ThemeDataStoreImpl(androidContext().dataStore)
    }
}

object ApiConfig {
    const val HH_BASE_URL = "https://api.hh.ru/"
    const val CSE_BASE_URL = "https://www.googleapis.com/customsearch/v1"
}

object DataStoreNames {
    const val SETTINGS_NAME = "settings"
}