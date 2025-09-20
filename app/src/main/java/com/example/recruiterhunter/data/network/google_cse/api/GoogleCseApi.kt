package com.example.recruiterhunter.data.network.google_cse.api

import com.example.recruiterhunter.BuildConfig
import com.example.recruiterhunter.data.dto.google_cse.response.CseResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GoogleCseApi {
    @GET("customsearch/v1")
    suspend fun search(
        @Query("key") apiKey: String = BuildConfig.GOOGLE_SEARCH_KEY,
        @Query("cx") cx: String = BuildConfig.CUSTOM_SEARCH_ENGINE,
        @Query("start") start: Int,
        @Query("q") query: String,
    ): CseResponseDto
}