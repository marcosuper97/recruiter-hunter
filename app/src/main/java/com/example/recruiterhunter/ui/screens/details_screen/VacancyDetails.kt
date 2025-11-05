package com.example.recruiterhunter.ui.screens.details_screen

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.fromHtml
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recruiterhunter.R
import com.example.recruiterhunter.presentation.detailsScreen_vm.DetailsScreenViewModel
import com.example.recruiterhunter.presentation.detailsScreen_vm.VacancyDetailsIntent
import com.example.recruiterhunter.ui.components.details_top_bar.DetailsTopBar
import com.example.recruiterhunter.ui.components.details_top_bar.TopAppBarDetailsDefaults
import com.example.recruiterhunter.ui.components.row_bar.RowBar
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
    val scrollState = rememberScrollState()
    val state by viewModel.screenState
    val density = LocalDensity.current

    LaunchedEffect(scrollState.value) {
        Log.d("Скроллстейт", scrollState.value.toString())
    }
    var maxTopBarHeight by rememberSaveable { mutableFloatStateOf(0f) }
    val minTopBarHeight = with(density) { TopAppBarDetailsDefaults.COLLAPSED.toPx() }
    val collapseRangePx = (maxTopBarHeight - minTopBarHeight).toFloat().coerceAtLeast(1f)
    val collapseProgress =
        if (!sharedTransitionScope.isTransitionActive)
            (scrollState.value / collapseRangePx).coerceIn(0f, 1f)
        else
            0f
    LaunchedEffect(vacancyId) {
        viewModel.sendIntent(VacancyDetailsIntent.FetchDetails(vacancyId))
    }

    BackHandler() {
        if (!sharedTransitionScope.isTransitionActive) {
            navController.popBackStack()
        }
    }
    Column {
        DetailsTopBar(
            vacancyId = vacancyId,
            vacancyName = vacancyName,
            employerName = employerName,
            employerLogo = employerLogo,
            address = address,
            salary = salary,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            navController = navController,
            appBarHeight = { barHeight -> maxTopBarHeight = barHeight },
            collapseProgress = { collapseProgress },
        )
        Spacer(Modifier.padding(vertical = 2.dp))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    state = scrollState,
                )
        ) {
            Text(
                text = stringResource(R.string.experience),
                style = vacancyDetailsTypo().detailsTitleText,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(Modifier.padding(vertical = 4.dp))
            Text(
                text = state.vacancyDetails?.experience ?: stringResource(R.string.not_specified),
                modifier = Modifier.padding(horizontal = 12.dp),
                style = vacancyDetailsTypo().detailsText
            )
            Spacer(Modifier.padding(vertical = 12.dp))
            RowBar(
                modifier = Modifier.padding(horizontal = 12.dp),
                barTitle = stringResource(R.string.key_skills),
                rowData = state.vacancyDetails?.keySkills
                    ?: listOf(stringResource(R.string.not_specified))
            )
            Spacer(Modifier.padding(vertical = 12.dp))
            RowBar(
                modifier = Modifier.padding(horizontal = 12.dp),
                barTitle = stringResource(R.string.work_format),
                rowData = state.vacancyDetails?.workFormat
                    ?: listOf(stringResource(R.string.not_specified))
            )
            Spacer(Modifier.padding(vertical = 12.dp))
            Text(
                text = stringResource(R.string.employment_form),
                style = vacancyDetailsTypo().detailsTitleText,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            Spacer(Modifier.padding(vertical = 4.dp))
            Text(
                text = state.vacancyDetails?.employmentForm
                    ?: stringResource(R.string.not_specified),
                modifier = Modifier.padding(horizontal = 12.dp),
                style = vacancyDetailsTypo().detailsText
            )

            Text(
                text = AnnotatedString.fromHtml(
                    state.vacancyDetails?.description ?: stringResource(R.string.not_specified)
                ),
                modifier = Modifier.padding(horizontal = 12.dp),
                style = vacancyDetailsTypo().detailsText
            )
        }
    }
}