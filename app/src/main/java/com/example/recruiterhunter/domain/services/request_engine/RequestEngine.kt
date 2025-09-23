package com.example.recruiterhunter.domain.services.request_engine

interface RequestEngine {
    suspend fun <T> doRequest(
        block: suspend () -> T
    ): Result<T>
}