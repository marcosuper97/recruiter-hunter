package com.example.recruiterhunter.domain.actions.source_code

interface OpenSourceCodeAction {
    suspend fun openRepository()
}