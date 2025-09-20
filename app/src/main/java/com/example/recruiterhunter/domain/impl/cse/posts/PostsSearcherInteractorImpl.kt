package com.example.recruiterhunter.domain.impl.cse.posts

import com.example.recruiterhunter.domain.interactor.cse.posts.PostsSearcherInteractor
import com.example.recruiterhunter.domain.repository.cse.posts.PostsSearcherRepository
import domain.model.cse.response_list.ResponseListCse

class PostsSearcherInteractorImpl(
    private val postsSearcherRepository: PostsSearcherRepository
) : PostsSearcherInteractor {
    override suspend fun doRequest(
        query: String,
        startIndex: Int
    ): Result<ResponseListCse> = postsSearcherRepository.doRequest(query, startIndex)
}