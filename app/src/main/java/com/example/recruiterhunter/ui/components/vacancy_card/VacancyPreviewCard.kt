package com.example.recruiterhunter.ui.components.vacancy_card

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.recruiterhunter.R
import com.example.recruiterhunter.domain.model.vacancy.preview.VacancyPreview
import com.example.recruiterhunter.ui.components.placeholder_icon.JobIcon
import com.example.recruiterhunter.ui.transition_keys.DetailsTransition

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun VacancyPreviewCard(
    onCardClick: (Long, String, String, String, String, String) -> Unit,
    vacancy: VacancyPreview,
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val themeTypo = MaterialTheme.typography
    val themeColors = MaterialTheme.colorScheme
    val vacancyNameStyle = themeTypo.titleMedium.copy(
        color = themeColors.onSurface,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Medium
    )
    val vacancyEmployerStyle = themeTypo.labelLarge.copy(
        color = themeColors.onSurface,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Light,
    )
    val addressTextStyle = themeTypo.labelMedium.copy(
        lineHeight = 14.sp,
        color = themeColors.onSurface,
    )
    val salaryTextStyle =
        themeTypo.bodyLarge.copy(
            color = themeColors.tertiary,
            fontWeight = FontWeight.Bold
        )
    val locationIcon = ImageVector.vectorResource(R.drawable.outline_location_on_24)
    val salary = formatSalary(
        salaryFrom = vacancy.salaryFrom,
        salaryTo = vacancy.salaryTo,
        currency = vacancy.currency
    )

    Card(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .clickable(
                enabled = true,
                onClick = {
                    onCardClick(
                        vacancy.vacancyId,
                        vacancy.vacancyName,
                        vacancy.employerName,
                        vacancy.employerLogo,
                        salary,
                        vacancy.address
                    )
                },
                role = Role.Button
            )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(verticalAlignment = Alignment.Top) {
                with(sharedTransitionScope) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .sharedElement(
                                sharedContentState = rememberSharedContentState(
                                    key = DetailsTransition.vacancyIdKey(
                                        vacancy.vacancyId
                                    )
                                ),
                                animatedVisibilityScope = animatedVisibilityScope
                            ),
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(vacancy.employerLogo)
                            .memoryCacheKey("vacancy_logo${vacancy.vacancyId}")
                            .placeholderMemoryCacheKey("vacancy_logo${vacancy.vacancyId}")
                            .build(),
                        contentScale = ContentScale.Fit,
                        contentDescription = "",
                        loading = { JobIcon() },
                        error = { JobIcon() },
                    )
                }
                Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                Column() {
                    Text(
                        text = vacancy.vacancyName,
                        style = vacancyNameStyle,
                    )
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    Text(
                        text = vacancy.employerName,
                        style = vacancyEmployerStyle,
                        modifier = Modifier.alpha(0.7f)
                    )
                }
            }
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = locationIcon,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                Text(
                    text = vacancy.address,
                    style = addressTextStyle,
                    fontWeight = FontWeight.Light
                )
            }
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            HorizontalDivider(Modifier.alpha(0.8f))
            Spacer(modifier = Modifier.padding(vertical = 6.dp))
            Row {
                Text(
                    text = salary,
                    style = salaryTextStyle,
                )
            }
        }
    }
}

@Composable
private fun formatSalary(salaryFrom: String, salaryTo: String, currency: String): String {
    return when {
        salaryFrom.toIntOrNull() == salaryTo.toIntOrNull() -> "$salaryFrom $currency"
        salaryFrom.isNotEmpty() && salaryTo.isEmpty() -> "От $salaryFrom $currency"
        salaryFrom.isEmpty() && salaryTo.isNotEmpty() -> "До $salaryTo $currency"
        salaryFrom.isNotEmpty() && salaryTo.isNotEmpty() -> "От $salaryFrom до $salaryTo $currency"
        else -> stringResource(R.string.salary_no_specified)
    }
}


//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
//@Composable
//fun PreviewCard(
//    vacancyPreview: VacancyPreview = VacancyPreview(
//        vacancyId = 111,
//        vacancyName = "Android developer в команду маркета",
//        employerName = "Yandex",
//        employerLogo = "https://img.hhcdn.ru/employer-logo/6459906.png",
//        address = "Улица Пушкина, дом колотушкина",
//        salaryFrom = "120 000",
//        salaryTo = "122 000",
//        currency = "Руб"
//    )
//) {
//    val state = rememberTextFieldState()
//    RecruiterHunterTheme(ActualTheme.DARK) {
//        Scaffold { innerPadding ->
//            Column {
//                SearchBarDock(state, label = "")
//                Spacer(modifier = Modifier.padding(vertical = 12.dp))
//                VacancyPreviewCard(
//                    {},
//                    vacancyPreview, modifier = Modifier
//                        .padding(innerPadding)
//                )
//            }
//        }
//    }
//}