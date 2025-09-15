package domain.model.vacancy

import domain.model.vacancy.preview.VacancyPreview

data class VacanciesList(
    val page: Int,
    val pages: Int,
    val found: Long,
    val vacanciesList: List<VacancyPreview>
)
