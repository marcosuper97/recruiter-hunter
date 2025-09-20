package com.example.recruiterhunter.domain.interactor.cse.recruiters

import domain.model.cse.response_list.ResponseListCse

interface RecruitersSearchInteractor {
    suspend fun doRequest(query: String, startIndex: Int = 1): Result<ResponseListCse>
}