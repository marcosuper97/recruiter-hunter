package com.example.recruiterhunter.domain.interactor.cse.posts

import domain.model.cse.response_list.ResponseListCse

interface PostsSearcherInteractor {
    suspend fun doRequest(query: String, startIndex: Int = 1): Result<ResponseListCse>
}