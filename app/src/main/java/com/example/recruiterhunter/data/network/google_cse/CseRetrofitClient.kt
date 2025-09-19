package com.example.recruiterhunter.data.network.google_cse

import android.content.Context
import com.example.recruiterhunter.data.dto.google_cse.response.CseResponseDto
import com.example.recruiterhunter.data.network.google_cse.api.GoogleCseApi

class CseRetrofitClient(
    private val context: Context,
    private val api: GoogleCseApi
) : GoogleNetworkClient {
    override suspend fun search(query: String): CseResponseDto {
        TODO("Not yet implemented")
    }
}