package domain.model.vacancy.preview

data class VacancyPreview(
    val vacancyId: String,
    val vacancyName: String,
    val employerName: String?,
    val employerLogo: String?,
    val address: String,
    val salaryFrom: Int?,
    val salaryTo: Int?,
    val currency: String?
)
