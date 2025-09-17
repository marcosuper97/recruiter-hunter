package com.example.recruiterhunter.domain.repository.cse.recruiters

import domain.model.cse.response_list.ResponseListCse

interface RecruitersSearcherRepository {
    suspend fun doRequest(): List<ResponseListCse>
}