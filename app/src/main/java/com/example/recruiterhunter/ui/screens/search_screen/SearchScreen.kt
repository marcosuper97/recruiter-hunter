package com.example.recruiterhunter.ui.screens.search_screen

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.tooling.preview.Preview
import com.example.recruiterhunter.R
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.presentation.seachVacancyVm.SearchVacancyViewModel
import com.example.recruiterhunter.presentation.seachVacancyVm.intents.SearchScreenIntent
import com.example.recruiterhunter.presentation.seachVacancyVm.intents.SearchScreenSideEffects
import com.example.recruiterhunter.ui.components.screen_states.ErrorStateScreen
import com.example.recruiterhunter.ui.components.search_bar.SearchBarDock
import com.example.recruiterhunter.ui.components.vacancy_card.SkeletonVacancyPreviewCard
import com.example.recruiterhunter.ui.components.vacancy_card.VacancyPreviewCard
import com.example.recruiterhunter.ui.theme.RecruiterHunterTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(viewModel: SearchVacancyViewModel = koinViewModel()) {

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
        when (sideEffect) {
            SearchScreenSideEffects.DownloadError -> {
                snackBarHostState.showSnackbar(errorString)
            }

            is SearchScreenSideEffects.OpenDetails -> {
//                navController.navigate("jobDetails/${sideEffect.vacancyId}")
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
                    Text(stringResource(R.string.app_name))
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
            if (screenState.hasContent) {
                LazyColumn(state = listState) {
                    itemsIndexed(
                        screenState.vacancyList,
                        key = { index, item -> "${index}_${item.vacancyId}" }) { index, item ->
                        VacancyPreviewCard(
                            onCardClick = {
                                viewModel.sendSideEffect(
                                    SearchScreenSideEffects.OpenDetails(
                                        item.vacancyId
                                    )
                                )
                            },
                            vacancy = item,
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
            if (screenState.loading) {
                LazyColumn {
                    items(4) {
                        SkeletonVacancyPreviewCard()
                    }
                }
            }
            if (screenState.error) {
                ErrorStateScreen(title = screenState.errorMessage)
            }
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    RecruiterHunterTheme(ActualTheme.LIGHT) {
        SearchScreen()
    }
}