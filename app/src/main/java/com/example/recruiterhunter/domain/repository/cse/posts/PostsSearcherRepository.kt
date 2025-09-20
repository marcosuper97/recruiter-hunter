package com.example.recruiterhunter.domain.repository.cse.posts

import domain.model.cse.response_list.ResponseListCse

interface PostsSearcherRepository {
    suspend fun doRequest(query: String, startIndex: Int): Result<ResponseListCse>
}