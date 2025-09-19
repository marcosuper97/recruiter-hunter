package com.example.recruiterhunter.data.converters.sce.full_response

import com.example.recruiterhunter.data.converters.sce.item.CseItemResponseConverter
import com.example.recruiterhunter.data.dto.google_cse.response.CseResponseDto
import domain.model.cse.response_list.ResponseListCse

class CseResponseConverterImpl(
    private val cseItemResponseConverter: CseItemResponseConverter
) :
    CseResponseConverter {
    override suspend fun map(cseResponseDto: CseResponseDto):
            ResponseListCse = ResponseListCse(
        itemsList = cseItemResponseConverter.map(cseResponseDto.items),
        startIndex = cseResponseDto.queries.nextPage?.get(0)?.startIndex ?: 0,
        totalResults = cseResponseDto.queries.nextPage?.get(0)?.totalResults ?: 0,
        searchTherms = cseResponseDto.queries.nextPage?.get(0)?.searchTherms ?: ""
    )
}