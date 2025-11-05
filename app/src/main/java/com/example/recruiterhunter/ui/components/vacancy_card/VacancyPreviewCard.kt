package com.example.recruiterhunter.ui.components.vacancy_card

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.recruiterhunter.R
import com.example.recruiterhunter.domain.model.vacancy.preview.VacancyPreview
import com.example.recruiterhunter.ui.components.employer_logo.buildImageRequest
import com.example.recruiterhunter.ui.components.placeholder_icon.JobIcon
import com.example.recruiterhunter.ui.theme.vacancyDetailsTypo
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
    val locationIcon = ImageVector.vectorResource(R.drawable.outline_location_on_24)

    with(sharedTransitionScope) {
        Card(
            modifier
                .fillMaxWidth()
                .sharedBounds(
                    rememberSharedContentState(key = DetailsTransition.containerKey(vacancy.vacancyId)),
                    animatedVisibilityScope = animatedVisibilityScope,
                    resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds,
                )
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    enabled = true,
                    onClick = {
                        onCardClick(
                            vacancy.vacancyId,
                            vacancy.vacancyName,
                            vacancy.employerName,
                            vacancy.employerLogo,
                            vacancy.address,
                            vacancy.salary,
                        )
                    },
                )
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(verticalAlignment = Alignment.Top) {
                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        model = buildImageRequest(vacancy.employerLogo, vacancy.vacancyId),
                        contentScale = ContentScale.Fit,
                        contentDescription = "",
                        loading = { JobIcon() },
                        error = { JobIcon() },
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                    Column() {
                        Text(
                            text = vacancy.vacancyName,
                            style = vacancyDetailsTypo().cardVacancyNameStyle,
                            modifier = Modifier.sharedBounds(
                                rememberSharedContentState(DetailsTransition.vacancyName(vacancy.vacancyId)),
                                animatedVisibilityScope = animatedVisibilityScope,
                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                            )
                        )
                        Spacer(modifier = Modifier.padding(vertical = 4.dp))
                        Text(
                            text = vacancy.employerName,
                            style = vacancyDetailsTypo().vacancyEmployerStyle,
                            modifier = Modifier
                                .alpha(0.7f)
                                .sharedBounds(
                                    rememberSharedContentState(DetailsTransition.salary(vacancy.vacancyId)),
                                    animatedVisibilityScope = animatedVisibilityScope,
                                    resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                                )
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = locationIcon,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .sharedElement(
                                rememberSharedContentState(
                                    DetailsTransition.navigationIcon(
                                        vacancy.vacancyId
                                    )
                                ),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                    )
                    Spacer(modifier = Modifier.padding(horizontal = 2.dp))
                    Text(
                        modifier = Modifier
                            .sharedBounds(
                                rememberSharedContentState(DetailsTransition.address(vacancy.vacancyId)),
                                animatedVisibilityScope = animatedVisibilityScope,
                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                            ),
                        text = vacancy.address,
                        style = vacancyDetailsTypo().addressTextStyle,
                        fontWeight = FontWeight.Light
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                HorizontalDivider(Modifier.alpha(0.8f))
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                Row {
                    Text(
                        text = vacancy.salary,
                        style = vacancyDetailsTypo().salaryTextStyle,
                    )
                }
            }
        }
    }
}