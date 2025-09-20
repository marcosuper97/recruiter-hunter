package com.example.recruiterhunter.data.network.google_cse

import com.example.recruiterhunter.data.dto.vacancies.response.details.VacancyDetailsResponseDto

class СseRequestBuilder(
    private val searchResources: SearchResources,
    private val searchType: SearchType,
    private val vacancyDetailsResponseDto: VacancyDetailsResponseDto
) {
    enum class SearchResources { LINKEDIN, SETKA, TENCHAT }
    enum class SearchType { POSTS, USERS }
}