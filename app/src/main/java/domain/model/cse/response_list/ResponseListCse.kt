package domain.model.cse.response_list

import domain.model.cse.item.ItemCse

data class ResponseListCse(
    val itemsList: List<ItemCse> = emptyList(),
    val startIndex: Int = 0,
    val totalResults: Int = 0,
    val searchTherms: String,
)