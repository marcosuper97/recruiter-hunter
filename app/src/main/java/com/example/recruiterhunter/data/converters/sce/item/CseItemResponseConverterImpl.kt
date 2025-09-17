package com.example.recruiterhunter.data.converters.sce.item

import com.example.recruiterhunter.data.dto.google_cse.response.CseItemResponseDto
import domain.model.cse.item.ItemCse

class CseItemResponseConverterImpl() : CseItemResponseConverter {
    override suspend fun map(cseItemResponseDto: CseItemResponseDto): ItemCse = ItemCse(
        title = cseItemResponseDto.pagemap.metatags.ogTitle ?: "Оглавление отсутствует",
        description = cseItemResponseDto.pagemap.metatags.ogDescription ?: "Описание отсутствует",
        imageUrl = cseItemResponseDto.pagemap.metatags.ogImage ?: "",
        linkUrl = cseItemResponseDto.pagemap.metatags.ogUrl
    )
}