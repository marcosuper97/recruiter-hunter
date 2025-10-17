package com.example.recruiterhunter.ui.screens.details_screen

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavController
import com.example.recruiterhunter.presentation.detailsScreen_vm.DetailsScreenViewModel
import com.example.recruiterhunter.ui.components.details_top_bar.DetailsTopBar
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
    var scrollableState by remember { mutableFloatStateOf(0f) }

    BackHandler() {
        if (!sharedTransitionScope.isTransitionActive) {
            navController.popBackStack()
        }
    }

    Column(Modifier
        .fillMaxSize()
        .scrollable(
            state = rememberScrollableState { offset ->
                scrollableState = scrollableState + offset
                offset
            },
            orientation = Orientation.Vertical
        )) {
        DetailsTopBar(
            modifier = Modifier,
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
}