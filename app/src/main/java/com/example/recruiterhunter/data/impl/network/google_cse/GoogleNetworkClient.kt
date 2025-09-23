package com.example.recruiterhunter.data.impl.network.google_cse

import com.example.recruiterhunter.data.dto.google_cse.response.CseResponseDto

interface GoogleNetworkClient {
    suspend fun search(query: String, startIndex: Int): Result<CseResponseDto>
}