package com.example.recruiterhunter.data.network.google_cse

import com.example.recruiterhunter.data.dto.vacancies.response.full.VacancyDetailsResponseDto

class СseRequestBuilder(
    private val searchResources: SearchResources,
    private val searchType: SearchType,
    private val vacancyDetailsResponseDto: VacancyDetailsResponseDto
) {
    enum class SearchResources { LINKEDIN, SETKA, TENCHAT }
    enum class SearchType { POSTS, USERS }

}


enum class SearchResources { LINKEDIN, SETKA, TENCHAT }
enum class SearchType { POSTS, USERS }

data class CseResponseDto(
    val items: List<Item>,
    val queries: Queries,
)

data class Item(
    val displayLink: String,
    val link: String,
    /**
     * Изучить Pagemap подробней
     */
    val pagemap: Pagemap,
    val snippet: String,
    val title: String
)

data class Queries(
    val nextPage: List<NextPage>,
    val request: List<Request>
)

data class Pagemap(
    val cse_image: List<CseImage>,
    val cse_thumbnail: List<CseThumbnail>,
    val metatags: List<Metatag>
)

data class CseImage(
    val src: String
)

data class CseThumbnail(
    val height: String,
    val src: String,
    val width: String
)

data class Metatag(
    val apple-itunes-app: String,
    val apple-mobile-web-app-capable: String,
    val mobile-web-app-capable: String,
    val og:description: String,
    val og:image: String,
    val og:image:height: String,
    val og:image:width: String,
    val og:site_name: String,
    val og:title: String,
    val og:type: String,
    val og:url: String,
    val translation_percentage: String,
    val twitter:card: String,
    val twitter:creator: String,
    val twitter:description: String,
    val twitter:image:src: String,
    val twitter:site: String,
    val twitter:title: String,
    val twitter:url: String,
    val viewport: String
)

data class NextPage(
    val count: Int,
    val cx: String,
    val inputEncoding: String,
    val linkSite: String,
    val outputEncoding: String,
    val safe: String,
    val searchTerms: String,
    val startIndex: Int,
    val title: String,
    val totalResults: String
)

data class Request(
    val count: Int,
    val cx: String,
    val linkSite: String,
    val safe: String,
    val searchTerms: String,
    val startIndex: Int,
    val title: String,
    val totalResults: String
)