package com.example.recruiterhunter.Infrastructure.network.request_engine

interface RequestEngine {
    suspend fun <T> doRequest(
        block: suspend () -> T
    ): Result<T>
}