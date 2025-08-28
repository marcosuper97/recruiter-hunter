package com.example.recruiterhunter.di

import com.example.recruiterhunter.data.network.google_cse.GoogleCseApi
import com.example.recruiterhunter.data.network.vacancies.HhApi
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

    single<GoogleCseApi> {
        Retrofit.Builder()
            .baseUrl(ApiConfig.CSE_BASE_URL)
            .addConverterFactory(
                json.asConverterFactory("application/json".toMediaType())
            )
            .build()
            .create(GoogleCseApi::class.java)

    }
}

object ApiConfig {
    const val HH_BASE_URL = "https://api.hh.ru/"
    const val CSE_BASE_URL = "https://www.googleapis.com/customsearch/v1"
}