package com.example.recruiterhunter.data.network.google_cse

interface GoogleNetworkClient {
    suspend fun search(query: String): List<CseResponseDto>
}