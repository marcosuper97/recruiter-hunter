package com.example.recruiterhunter.data.dto.google_cse.response

import com.example.recruiterhunter.data.dto.google_cse.response.response_elements.PageMapDto
import kotlinx.serialization.Serializable

@Serializable
data class CseItemResponseDto(
    val pagemap: PageMapDto,
)