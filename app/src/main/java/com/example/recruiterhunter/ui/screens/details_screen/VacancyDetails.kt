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
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavController
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recruiterhunter.R
import com.example.recruiterhunter.presentation.detailsScreen_vm.DetailsScreenViewModel
import com.example.recruiterhunter.ui.transition_keys.DetailsTransition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun VacancyDetails(
    vacancyId: Long? = null,
    vacancyName: String? = null,
    employerName: String? = null,
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

    val scope = rememberCoroutineScope()
    val materialThemeColors = MaterialTheme.colorScheme
    var backgroundColor by remember(LocalContext.current) {
        mutableStateOf(materialThemeColors.onSurface)
    }
    var onBackgroundColors by remember(LocalContext.current) {
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
                                .background(backgroundColor)
                                .fillMaxWidth()
                                .height(
                                    100.dp,
                                )
                                .align(Alignment.TopCenter)
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_back_24),
                            tint = onBackgroundColors,
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
                                scope = scope,
                                generatedBackgroundColors = { color -> backgroundColor = color },
                                generatedOnBackgroundColors = { color ->
                                    onBackgroundColors = color
                                },
                            )
                        }
                    }
                    Spacer(Modifier.padding(vertical = 6.dp))
                    Text(
                        text = vacancyName ?: "",
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

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun EmployerLogo(
    vacancyId: Long? = null,
    employerLogo: String? = null,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    scope: CoroutineScope,
    generatedBackgroundColors: (Color) -> Unit,
    generatedOnBackgroundColors: (Color) -> Unit
) {
    with(sharedTransitionScope) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(4.dp))
                .sharedElement(
                    sharedContentState = rememberSharedContentState(
                        key = DetailsTransition.vacancyIdKey(
                            vacancyId
                        )
                    ),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
            model = ImageRequest.Builder(LocalContext.current)
                .data(employerLogo)
                .memoryCacheKey("vacancy_logo${vacancyId}")
                .placeholderMemoryCacheKey("vacancy_logo${vacancyId}")
                .allowHardware(false)
                .build(),
            contentScale = ContentScale.Fit,
            contentDescription = "",
            onSuccess = { state ->
                scope.launch(Dispatchers.Default) {
                    val image = state.result.drawable.toBitmap()
                    val palette = Palette.from(image).generate()

                    palette.dominantSwatch?.let { swatch ->
                        withContext(Dispatchers.Main) {
                            generatedBackgroundColors(Color(swatch.rgb))
                            generatedOnBackgroundColors(Color(swatch.titleTextColor))
                        }
                    }
                }
            }
        )

    }
}
