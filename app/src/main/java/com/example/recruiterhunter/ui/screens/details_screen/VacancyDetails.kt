package com.example.recruiterhunter.ui.screens.details_screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recruiterhunter.R
import com.example.recruiterhunter.presentation.detailsScreen_vm.DetailsScreenViewModel
import com.example.recruiterhunter.presentation.detailsScreen_vm.VacancyDetailsIntent
import com.example.recruiterhunter.ui.components.details_top_bar.DetailsTopBar
import com.example.recruiterhunter.ui.theme.vacancyDetailsTypo
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
    val state by viewModel.screenState

    LaunchedEffect(vacancyId) {
        viewModel.sendIntent(VacancyDetailsIntent.FetchDetails(vacancyId))
    }

    BackHandler() {
        if (!sharedTransitionScope.isTransitionActive) {
            navController.popBackStack()
        }
    }

    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .scrollable(
                state = rememberScrollableState { offset ->
                    scrollableState = scrollableState + offset
                    offset
                },
                orientation = Orientation.Vertical
            )
    ) {
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
        Spacer(Modifier.padding(vertical = 12.dp))
        Text(
            text = stringResource(R.string.experience),
            style = vacancyDetailsTypo().detailsTitleText
        )
        Spacer(Modifier.padding(vertical = 6.dp))
        Text(text = state.vacancyDetails?.experience ?: stringResource(R.string.not_specified))
//        KeySkillsBar()
    }
}