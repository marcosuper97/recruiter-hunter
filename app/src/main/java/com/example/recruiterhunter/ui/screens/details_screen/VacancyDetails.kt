package com.example.recruiterhunter.ui.screens.details_screen

import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.platform.WindowInfo
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recruiterhunter.R
import com.example.recruiterhunter.presentation.detailsScreen_vm.DetailsScreenViewModel
import com.example.recruiterhunter.ui.components.employer_logo.EmployerLogo
import com.example.recruiterhunter.ui.transition_keys.DetailsTransition
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VacancyDetails(
    vacancyId: Long,
    vacancyName: String,
    employerName: String?,
    employerLogo: String? = null,
    address: String? = null,
    salary: String? = null,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController,
    viewModel: DetailsScreenViewModel = koinViewModel()
) {
    BackHandler() {
        if (!sharedTransitionScope.isTransitionActive) {
            navController.popBackStack()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            DetailsTopBar(
                modifier = Modifier.statusBarsPadding().padding(top = 12.dp),
                vacancyId = vacancyId,
                vacancyName = vacancyName,
                employerName = employerName,
                employerLogo = employerLogo,
                address = address,
                salary = salary,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                navController = navController
            )
        }
    ) { innerPaddings ->
//        DetailsTopBar(
//            modifier = Modifier.padding(innerPaddings),
//            vacancyId = vacancyId,
//            vacancyName = vacancyName,
//            employerName = employerName,
//            employerLogo = employerLogo,
//            address = address,
//            salary = salary,
//            sharedTransitionScope = sharedTransitionScope,
//            animatedVisibilityScope = animatedVisibilityScope,
//            navController = navController
//        )
    }
}


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
    val themeTypo = MaterialTheme.typography
    val vacancyNameStyle = themeTypo.titleLarge.copy(color = themeColors.onSurface)
    var topBackgroundColor by remember(LocalContext.current) {
        mutableStateOf(themeColors.onSurface)
    }
    var elementsColor by remember(LocalContext.current) {
        mutableStateOf(themeColors.onSurfaceVariant)
    }
    val logoShape = CircleShape

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
                                topBackgroundColor.copy(0.4f),
                                topBackgroundColor.copy(0.2f),
                                topBackgroundColor.copy(0.12f),
                                topBackgroundColor.copy(0.06f),
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent,
                                Color.Transparent
                            ),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
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
                EmployerLogo(
                    modifier = Modifier
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
                Text(
                    text = vacancyName,
                    textAlign = TextAlign.Center,
                    style = vacancyNameStyle,
                    fontWeight = FontWeight.Bold,
                    color = themeColors.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .sharedBounds(
                            rememberSharedContentState(DetailsTransition.vacancyName(vacancyId)),
                            animatedVisibilityScope = animatedVisibilityScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                        )
                )
                Spacer(Modifier.padding(vertical = 6.dp))
                Text(
                    text = employerName ?: "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = themeColors.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .sharedBounds(
                            rememberSharedContentState(DetailsTransition.employerName(vacancyId)),
                            animatedVisibilityScope = animatedVisibilityScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                        )
                )
                Spacer(Modifier.padding(vertical = 6.dp))
                Text(
                    text = address ?: "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = themeColors.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                        .sharedBounds(
                            rememberSharedContentState(DetailsTransition.address(vacancyId)),
                            animatedVisibilityScope = animatedVisibilityScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                        )
                )
                Spacer(Modifier.padding(vertical = 6.dp))
                Text(
                    text = salary ?: "",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = themeColors.onSurface,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
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