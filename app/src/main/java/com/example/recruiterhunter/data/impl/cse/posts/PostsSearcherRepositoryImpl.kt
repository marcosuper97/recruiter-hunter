package com.example.recruiterhunter.data.impl.cse.posts

import com.example.recruiterhunter.data.converters.sce.full_response.CseResponseConverter
import com.example.recruiterhunter.data.impl.network.google_cse.GoogleNetworkClient
import com.example.recruiterhunter.domain.repository.cse.posts.PostsSearcherRepository
import domain.model.cse.response_list.ResponseListCse

class PostsSearcherRepositoryImpl(
    private val cseResponseConverter: CseResponseConverter,
    private val googleNetworkClient: GoogleNetworkClient
) : PostsSearcherRepository {
    override suspend fun doRequest(
        query: String,
        startIndex: Int
    ): Result<ResponseListCse> = googleNetworkClient
        .search(query, startIndex)
        .map { data ->
            cseResponseConverter.map(data)
        }

}