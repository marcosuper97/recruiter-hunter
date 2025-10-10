package domain.model.vacancy

import androidx.compose.runtime.Immutable
import com.example.recruiterhunter.domain.model.vacancy.preview.VacancyPreview

@Immutable
data class VacanciesList(
    val page: Int,
    val pages: Int,
    val found: Long,
    val vacanciesList: List<VacancyPreview>
)
