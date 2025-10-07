package com.example.recruiterhunter.ui.screens.search_screen

import android.net.Uri
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.recruiterhunter.R
import com.example.recruiterhunter.presentation.seachVacancyVm.SearchVacancyViewModel
import com.example.recruiterhunter.presentation.seachVacancyVm.intents.SearchScreenIntent
import com.example.recruiterhunter.presentation.seachVacancyVm.intents.SearchScreenSideEffects
import com.example.recruiterhunter.ui.components.screen_states.ErrorStateScreen
import com.example.recruiterhunter.ui.components.search_bar.SearchBarDock
import com.example.recruiterhunter.ui.components.vacancy_card.SkeletonVacancyPreviewCard
import com.example.recruiterhunter.ui.components.vacancy_card.VacancyPreviewCard
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun SearchScreen(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    viewModel: SearchVacancyViewModel = koinViewModel()
) {

    val screenState by viewModel.screenState
    val filterIcon = ImageVector.vectorResource(R.drawable.baseline_filter_list_24)
    val searchIcon = ImageVector.vectorResource(R.drawable.baseline_search_24)
    val sideEffect by viewModel.sideEffect.collectAsState(null)
    val snackBarHostState = remember { SnackbarHostState() }
    val errorString = stringResource(R.string.download_error)
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val themeColors = MaterialTheme.colorScheme

    LaunchedEffect(sideEffect) {
        when (val sideEffect = sideEffect) {
            SearchScreenSideEffects.DownloadError -> {
                snackBarHostState.showSnackbar(errorString)
            }

            is SearchScreenSideEffects.OpenDetails -> {
                navController.navigate("job_detail/${sideEffect.vacancyId}/${sideEffect.vacancyName}/${sideEffect.employerName}/${sideEffect.employerLogo}/${sideEffect.address}/${sideEffect.salary}")
            }

            SearchScreenSideEffects.OpenFilters -> {
                // Открытие фильтров (если через navController или другой способ)
            }

            null -> {}
        }
    }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = {
                    Column(modifier = Modifier.padding(end = 12.dp, top = 24.dp, bottom = 12.dp)) {
                        Text(
                            stringResource(R.string.app_name),
                            style = MaterialTheme.typography.headlineSmall
                        )
                        Spacer(Modifier.padding(vertical = 8.dp))
                        SearchBarDock(
                            state = viewModel.textField,
                            labelIcon = searchIcon,
                            filterIcon = filterIcon,
                            onSearch = {
                                viewModel.sendIntent(SearchScreenIntent.DoNewRequest)
                                scope.launch { listState.scrollToItem(0) }
                            },
                            onFilterClick = { viewModel.sendSideEffect(SearchScreenSideEffects.OpenFilters) },
                            filterState = screenState.hasAnyFilters,
                            label = stringResource(R.string.search)
                        )

                    }
                },
                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = themeColors.surface,
                    scrolledContainerColor = themeColors.surface
                )
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPaddings ->
        Column(modifier = Modifier.padding(innerPaddings)) {
            if (screenState.hasContent) {
                LazyColumn(state = listState) {
                    itemsIndexed(
                        screenState.vacancyList,
                        key = { index, item -> "${index}_${item.vacancyId}" }) { index, item ->
                        VacancyPreviewCard(
                            onCardClick = { vacancyId, vacancyName, employerName, employerLogo, address, salary ->
                                viewModel.sendSideEffect(
                                    SearchScreenSideEffects.OpenDetails(
                                        vacancyId = vacancyId,
                                        vacancyName = Uri.encode(vacancyName),
                                        employerName = Uri.encode(employerName),
                                        employerLogo = Uri.encode(employerLogo),
                                        address = Uri.encode(address),
                                        salary = salary
                                    )
                                )
                            },
                            vacancy = item,
                            sharedTransitionScope = sharedTransitionScope,
                            animatedVisibilityScope = animatedVisibilityScope,
                        )
                    }
                    if (screenState.loadingNextPage)
                        items(1) {
                            SkeletonVacancyPreviewCard()
                        }
                }
                LaunchedEffect(listState) {
                    snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
                        .collect { lastVisibleIdx ->
                            if (
                                lastVisibleIdx == screenState.vacancyList.lastIndex && !screenState.loadingNextPage
                            ) {
                                viewModel.sendIntent(SearchScreenIntent.LoadNextPage)
                            }
                        }
                }
            }

            when {
                screenState.loading -> LazyColumn {
                    items(4) {
                        SkeletonVacancyPreviewCard()
                    }
                }

                screenState.emptyResult -> ErrorStateScreen(
                    title = stringResource(R.string.nothing_found),
                    message = stringResource(R.string.nothing_found_hint),
                    iconState = ImageVector.vectorResource(R.drawable.cow_01)
                )

                screenState.internetHasNotAvailable -> ErrorStateScreen(
                    title = stringResource(R.string.internet_is_unavailable),
                    message = stringResource(R.string.internet_is_unavailable_hint),
                    iconState = ImageVector.vectorResource(R.drawable.error_naughty_dog)
                )

                screenState.networkError -> ErrorStateScreen(
                    title = stringResource(R.string.internet_is_unavailable),
                    message = stringResource(R.string.internet_is_unavailable_hint),
                    iconState = ImageVector.vectorResource(R.drawable.error_naughty_dog)
                )

                screenState.authorizationError || screenState.clientError -> ErrorStateScreen(
                    title = stringResource(R.string.authorization_error),
                    message = stringResource(R.string.authorization_error_hint),
                    iconState = ImageVector.vectorResource(R.drawable.error_rocket_destroyed)
                )

                screenState.serverError -> ErrorStateScreen(
                    title = stringResource(R.string.server_error),
                    message = stringResource(R.string.server_error_hint),
                    iconState = ImageVector.vectorResource(R.drawable.tissue_01)
                )

                screenState.unknownError -> ErrorStateScreen(
                    title = stringResource(R.string.unknown_error),
                    message = stringResource(R.string.unknown_error_hint),
                    iconState = ImageVector.vectorResource(R.drawable.lochness_monster_01)
                )
            }
        }
    }
}