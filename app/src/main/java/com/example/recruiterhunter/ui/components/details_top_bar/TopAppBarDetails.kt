package com.example.recruiterhunter.ui.components.details_top_bar

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recruiterhunter.R
import com.example.recruiterhunter.ui.components.employer_logo.EmployerLogo
import com.example.recruiterhunter.ui.theme.vacancyDetailsTypo
import com.example.recruiterhunter.ui.transition_keys.DetailsTransition

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsTopBar(
    modifier: Modifier = Modifier,
    vacancyId: Long,
    vacancyName: String,
    employerName: String?,
    employerLogo: String? = null,
    address: String? = null,
    salary: String? = null,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController,
) {


    val themeColors = MaterialTheme.colorScheme
    var topBackgroundColor by remember {
        mutableStateOf(themeColors.onSurface)
    }
    var elementsColor by remember {
        mutableStateOf(themeColors.onSurfaceVariant)
    }
    val employerLogoIsEmpty by remember { mutableStateOf(employerLogo != "") }
    val logoShape = CircleShape
    val locationIcon = ImageVector.vectorResource(R.drawable.outline_location_on_24)
    val paddingTop = LocalView.current.paddingTop.dp + 42.dp
    with(sharedTransitionScope) {
        Box(
            modifier = modifier
                .dropShadow(RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp)) {
                    radius = 16f
                    spread = 8f
                    color = Color(0x30000000)
                    offset = Offset(0f, 6f)
                    alpha = 0.85f
                }
                .background(
                    themeColors.surfaceContainer,
                    shape = RoundedCornerShape(bottomEnd = 24.dp, bottomStart = 24.dp)
                )
                .fillMaxWidth()
                .sharedBounds(
                    rememberSharedContentState(key = DetailsTransition.containerKey(vacancyId)),
                    animatedVisibilityScope = animatedVisibilityScope,
                    resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds,
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                topBackgroundColor.copy(1f),
                                topBackgroundColor.copy(0.8f),
                                topBackgroundColor.copy(0.6f),
                                topBackgroundColor.copy(0.2f),
                                topBackgroundColor.copy(0.06f),
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.padding(top = paddingTop))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_back_24),
                        tint = elementsColor,
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                enabled = true,
                                onClick = {
                                    if (!sharedTransitionScope.isTransitionActive) {
                                        navController.popBackStack()
                                    }
                                },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            )
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_share_24),
                        tint = elementsColor,
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                enabled = true,
                                onClick = {
                                    if (!sharedTransitionScope.isTransitionActive) {
                                        navController.popBackStack()
                                    }
                                },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            )
                    )

                    Spacer(Modifier.padding(horizontal = 12.dp))

                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.outline_bookmark_add_24),
                        tint = elementsColor,
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                enabled = true,
                                onClick = {
                                    if (!sharedTransitionScope.isTransitionActive) {
                                        navController.popBackStack()
                                    }
                                },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            )
                    )


                }
                if (employerLogoIsEmpty == true) {
                    EmployerLogo(
                        modifier = Modifier
                            .padding(top = 12.dp)
                            .dropShadow(
                                shape = CircleShape
                            ) {
                                offset = Offset(x = 0f, y = 8f)
                                spread = 2f
                                radius = 18f
                                alpha = 0.4f
                                color = topBackgroundColor
                            }
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        topBackgroundColor.copy(0.16f),
                                        topBackgroundColor.copy(0.08f),
                                        topBackgroundColor.copy(0.02f),
                                    ),
                                    startY = 0f,
                                    endY = Float.POSITIVE_INFINITY
                                ),
                                shape = logoShape
                            )
                            .border(4.dp, topBackgroundColor, logoShape)
                            .size(100.dp)
                            .clip(logoShape),
                        vacancyId = vacancyId,
                        employerLogo = employerLogo,
                        generatedBackgroundColors = { backgroundColor, elementColors ->
                            topBackgroundColor = backgroundColor
                            elementsColor = elementColors
                        }
                    )
                }
                Spacer(Modifier.padding(vertical = 12.dp))
                Text(
                    text = vacancyName,
                    textAlign = TextAlign.Center,
                    style = vacancyDetailsTypo().cardVacancyNameStyle,
                    modifier = Modifier
                        .sharedBounds(
                            rememberSharedContentState(DetailsTransition.vacancyName(vacancyId)),
                            animatedVisibilityScope = animatedVisibilityScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                        )
                )
                Spacer(Modifier.padding(vertical = 2.dp))
                Text(
                    text = employerName ?: "",
                    textAlign = TextAlign.Center,
                    style = vacancyDetailsTypo().vacancyEmployerStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.7f)
                        .sharedBounds(
                            rememberSharedContentState(DetailsTransition.employerName(vacancyId)),
                            animatedVisibilityScope = animatedVisibilityScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                        )
                )
                Spacer(Modifier.padding(vertical = 4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    Icon(
                        imageVector = locationIcon,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .sharedElement(
                                rememberSharedContentState(
                                    DetailsTransition.navigationIcon(
                                        vacancyId
                                    )
                                ),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                    )
                    Text(
                        text = address ?: "",
                        textAlign = TextAlign.Center,
                        style = vacancyDetailsTypo().addressTextStyle,
                        modifier = Modifier
                            .sharedBounds(
                                rememberSharedContentState(DetailsTransition.address(vacancyId)),
                                animatedVisibilityScope = animatedVisibilityScope,
                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                            )
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
                HorizontalDivider(Modifier.alpha(0.8f))
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                Text(
                    text = salary ?: "",
                    textAlign = TextAlign.Center,
                    style = vacancyDetailsTypo().salaryTextStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .sharedBounds(
                            rememberSharedContentState(DetailsTransition.salary(vacancyId)),
                            animatedVisibilityScope = animatedVisibilityScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                        )
                )
            }
        }
    }
}