package com.example.recruiterhunter.data.converters.sce.item

import com.example.recruiterhunter.data.dto.google_cse.response.CseItemResponseDto
import domain.model.cse.item.ItemCse

interface CseItemResponseConverter {
    suspend fun map(cseItemResponseDto: List<CseItemResponseDto>?): List<ItemCse>
}