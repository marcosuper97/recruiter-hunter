package com.example.recruiterhunter.data.network.google_cse

import com.example.recruiterhunter.core.network.request_engine.RequestEngine
import com.example.recruiterhunter.data.dto.google_cse.response.CseResponseDto
import com.example.recruiterhunter.data.network.google_cse.api.GoogleCseApi

class CseRetrofitClient(
    private val requestEngine: RequestEngine,
    private val api: GoogleCseApi
) : GoogleNetworkClient {
    override suspend fun search(query: String, startIndex: Int): Result<CseResponseDto> =
        requestEngine.doRequest { api.search(query = query, start = startIndex) }
}