package com.example.recruiterhunter.data.network.google_cse

import com.example.recruiterhunter.data.dto.google_cse.response.CseResponseDto

interface GoogleNetworkClient {
    suspend fun search(query: String): List<CseResponseDto>
}