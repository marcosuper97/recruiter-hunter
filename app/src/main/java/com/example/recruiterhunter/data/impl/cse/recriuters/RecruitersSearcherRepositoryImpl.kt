package com.example.recruiterhunter.data.impl.cse.recriuters

import com.example.recruiterhunter.data.converters.sce.full_response.CseResponseConverter
import com.example.recruiterhunter.data.network.google_cse.GoogleNetworkClient
import com.example.recruiterhunter.domain.repository.cse.recruiters.RecruitersSearcherRepository
import domain.model.cse.response_list.ResponseListCse

class RecruitersSearcherRepositoryImpl(
    private val cseResponseConverter: CseResponseConverter,
    private val googleNetworkClient: GoogleNetworkClient
) :
    RecruitersSearcherRepository {
    override suspend fun doRequest(
        query: String,
        startIndex: Int
    ): Result<ResponseListCse> = googleNetworkClient
        .search(query, startIndex)
        .map { data ->
            cseResponseConverter.map(data)
        }
}