package com.example.recruiterhunter.di

import androidx.room.Room
import com.example.recruiterhunter.data.local.db.AppDb
import com.example.recruiterhunter.data.local.filters.entity.FiltersEntity
import com.example.recruiterhunter.data.network.google_cse.GoogleCseApi
import com.example.recruiterhunter.data.network.vacancies.HhApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
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

    single {
        val db = Room.databaseBuilder(androidContext(), AppDb::class.java, "database.db")
            .fallbackToDestructiveMigration(false)
            .build()
        val dao = db.filtersDao()
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(
                FiltersEntity(
                    id = 1
                )
            )
        }
        db
    }
}

object ApiConfig {
    const val HH_BASE_URL = "https://api.hh.ru/"
    const val CSE_BASE_URL = "https://www.googleapis.com/customsearch/v1"
}