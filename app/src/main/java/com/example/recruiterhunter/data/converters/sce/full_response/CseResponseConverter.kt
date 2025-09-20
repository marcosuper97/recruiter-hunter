package com.example.recruiterhunter.data.converters.sce.full_response

import com.example.recruiterhunter.data.dto.google_cse.response.CseResponseDto
import domain.model.cse.response_list.ResponseListCse

interface CseResponseConverter {
    suspend fun map(cseResponseDto: CseResponseDto): ResponseListCse
}