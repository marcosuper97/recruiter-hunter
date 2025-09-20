package com.example.recruiterhunter.data.dto.google_cse.request

data class RequestCseDto(
    val query: String,
    val startIndex: Int = 1,
)