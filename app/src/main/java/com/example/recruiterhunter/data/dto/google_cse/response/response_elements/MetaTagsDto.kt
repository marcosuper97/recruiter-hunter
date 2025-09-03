package com.example.recruiterhunter.data.dto.google_cse.response.response_elements

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MetaTagsDto(
    @SerialName("og:image")
    val ogImage: String? = null,
    @SerialName("og:title")
    val ogTitle: String? = null,
    @SerialName("og:description")
    val ogDescription: String? = null,
    @SerialName("og:url")
    val ogUrl: String
)
