package com.example.recruiterhunter.core.network.request_engine

interface RequestEngine {
    suspend fun <T> doRequest(
        block: suspend () -> T
    ): Result<T>
}