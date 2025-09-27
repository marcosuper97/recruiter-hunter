package com.example.recruiterhunter.data.impl.network.google_cse

import com.example.recruiterhunter.data.dto.google_cse.response.CseResponseDto
import com.example.recruiterhunter.data.impl.network.google_cse.api.GoogleCseApi
import com.example.recruiterhunter.domain.services.request_engine.RequestEngine

class CseRetrofitClient(
    private val requestEngine: RequestEngine,
    private val api: GoogleCseApi
) : GoogleNetworkClient {
    override suspend fun search(query: String, startIndex: Int): Result<CseResponseDto> =
        requestEngine.doRequest { api.search(query = query, start = startIndex) }
}