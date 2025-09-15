package com.example.recruiterhunter.core.network

import android.content.Context

interface RequestEngine {
    suspend fun <T> doRequest(
        block: suspend () -> T
    ): Result<T>
}