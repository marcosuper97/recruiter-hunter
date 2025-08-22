package com.example.recruiterhunter.data.dto.vacancies.filteres.areas

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AreaDto(
    val id: String,
    @SerialName("parent_id")
    val parentId: String?,
    val name: String,
    val areas: List<AreaDto>
)