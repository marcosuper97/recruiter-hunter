package domain.model.filters

data class Areas(
    val id: String,
    val parentId: String?,
    val name: String,
    val areas: List<Areas>
)
