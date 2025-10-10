package com.example.recruiterhunter.ui.screens.details_screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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

    val materialThemeColors = MaterialTheme.colorScheme
    var topBackgroundColor by remember(LocalContext.current) {
        mutableStateOf(materialThemeColors.onSurface)
    }
    var elementsColor by remember(LocalContext.current) {
        mutableStateOf(materialThemeColors.onSurfaceVariant)
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPaddings ->
        with(sharedTransitionScope) {
            Box(
                modifier = Modifier
                    .background(materialThemeColors.surfaceContainer)
                    .fillMaxSize()
                    .padding(innerPaddings)
                    .sharedBounds(
                        rememberSharedContentState(key = DetailsTransition.containerKey(vacancyId)),
                        animatedVisibilityScope = animatedVisibilityScope,
                        resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds,
                    )
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Box(
                        modifier = Modifier
                            .background(Color.Transparent)
                            .fillMaxWidth()
                            .height(
                                150.dp,
                            )
                    ) {
                        Box(
                            modifier = Modifier
                                .background(topBackgroundColor)
                                .fillMaxWidth()
                                .height(
                                    100.dp,
                                )
                                .align(Alignment.TopCenter)
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_back_24),
                            tint = elementsColor,
                            contentDescription = "",
                            modifier = Modifier.padding(12.dp)
                        )
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(
                                modifier = Modifier
                                    .padding(top = 50.dp)
                                    .background(Color.Transparent)
                            )
                            EmployerLogo(
                                vacancyId = vacancyId,
                                employerLogo = employerLogo,
                                sharedTransitionScope = sharedTransitionScope,
                                animatedVisibilityScope = animatedVisibilityScope,
                                generatedBackgroundColors = { backgroundColor, elementColors ->
                                    topBackgroundColor = backgroundColor
                                    elementsColor = elementColors

                                }
                            )
                        }
                    }
                    Spacer(Modifier.padding(vertical = 6.dp))
                    Text(
                        text = vacancyName,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = materialThemeColors.onSurface,
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
                        color = materialThemeColors.onSurface,
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
                        color = materialThemeColors.onSurface,
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
                        color = materialThemeColors.onSurface,
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
}