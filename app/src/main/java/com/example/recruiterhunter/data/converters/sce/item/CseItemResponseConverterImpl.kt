package com.example.recruiterhunter.data.converters.sce.item

import com.example.recruiterhunter.data.dto.google_cse.response.CseItemResponseDto
import domain.model.cse.item.ItemCse

class CseItemResponseConverterImpl() : CseItemResponseConverter {
    override suspend fun map(cseItemResponseDto: List<CseItemResponseDto>?): List<ItemCse> =
        cseItemResponseDto?.map { cseItemResponseDto ->
            ItemCse(
                title = cseItemResponseDto.pagemap.metatags.ogTitle ?: "",
                description = cseItemResponseDto.pagemap.metatags.ogDescription ?: "",
                imageUrl = cseItemResponseDto.pagemap.metatags.ogImage ?: "",
                linkUrl = cseItemResponseDto.pagemap.metatags.ogUrl
            )
        } ?: emptyList()
}