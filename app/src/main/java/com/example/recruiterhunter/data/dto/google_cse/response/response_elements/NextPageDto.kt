package com.example.recruiterhunter.data.dto.google_cse.response.response_elements

import kotlinx.serialization.Serializable

@Serializable
data class NextPageDto(
    val startIndex: Int,
    val totalResults: String,
    val searchTherms: String,
)