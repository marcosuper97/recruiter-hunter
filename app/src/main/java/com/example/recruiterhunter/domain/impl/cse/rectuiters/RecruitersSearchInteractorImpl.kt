package com.example.recruiterhunter.domain.impl.cse.rectuiters

import com.example.recruiterhunter.domain.interactor.cse.recruiters.RecruitersSearchInteractor
import com.example.recruiterhunter.domain.repository.cse.recruiters.RecruitersSearcherRepository
import domain.model.cse.response_list.ResponseListCse

class RecruitersSearchInteractorImpl(
    private val recruitersSearcherRepository: RecruitersSearcherRepository
) :
    RecruitersSearchInteractor {
    override suspend fun doRequest(
        query: String,
        startIndex: Int
    ): Result<ResponseListCse> = recruitersSearcherRepository.doRequest(query, startIndex)
}