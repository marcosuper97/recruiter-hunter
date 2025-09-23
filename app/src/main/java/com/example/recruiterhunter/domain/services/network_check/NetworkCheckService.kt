package com.example.recruiterhunter.domain.services.network_check

interface NetworkCheckService {
    suspend fun isInternetAvailable(): Boolean
}