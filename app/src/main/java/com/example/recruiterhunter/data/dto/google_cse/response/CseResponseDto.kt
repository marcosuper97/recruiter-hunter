package com.example.recruiterhunter.data.dto.google_cse.response

import com.example.recruiterhunter.data.dto.google_cse.response.response_elements.QueriesDto
import kotlinx.serialization.Serializable

@Serializable
data class CseResponseDto(
    val items: List<CseItemResponseDto>?,
    val queries: QueriesDto,
)