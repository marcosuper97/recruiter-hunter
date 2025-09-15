package domain.model.filters

data class Filters(
    val country: String?,
    val countryId: String?,
    val area: String?,
    val areaId: String?,
    val industry: String?,
    val industryId: String?,
    val salary: String?,
    val onlyWithSalary: Boolean?
)
