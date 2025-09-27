package com.example.recruiterhunter.di

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
import com.example.recruiterhunter.domain.services.request_engine.RequestEngine
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private val json = Json {
    ignoreUnknownKeys = true
}

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

    single<RequestEngine> {
        RequestEngineImpl(get())
    }
}

private object ApiConfig {
    const val HH_BASE_URL = "https://api.hh.ru/"
    const val CSE_BASE_URL = "https://www.googleapis.com/customsearch/v1"
}