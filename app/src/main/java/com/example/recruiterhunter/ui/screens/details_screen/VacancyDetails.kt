package com.example.recruiterhunter.ui.screens.details_screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.recruiterhunter.presentation.detailsScreen_vm.DetailsScreenViewModel
import com.example.recruiterhunter.ui.components.placeholder_icon.JobIcon
import com.example.recruiterhunter.ui.transition_keys.DetailsTransition
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
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
    viewModel: DetailsScreenViewModel = koinViewModel()
) {
    with(sharedTransitionScope) {
        Scaffold() { innerPaddings ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPaddings)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                ) {
                    SubcomposeAsyncImage(
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
                            .build(),
                        contentScale = ContentScale.Fit,
                        contentDescription = "",
                        loading = { JobIcon() },
                        error = { JobIcon() }
                    )
                }
            }
        }
    }
}


