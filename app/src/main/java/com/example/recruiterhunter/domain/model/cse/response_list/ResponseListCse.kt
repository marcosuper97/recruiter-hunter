package domain.model.cse.response_list

import domain.model.cse.item.ItemCse

data class ResponseListCse(
    val itemsList: List<ItemCse>,
    val startIndex: Int,
    val totalResults: Int,
    val searchTherms: String,
)