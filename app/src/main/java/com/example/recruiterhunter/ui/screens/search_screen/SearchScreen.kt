package com.example.recruiterhunter.ui.screens.search_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.recruiterhunter.R
import com.example.recruiterhunter.presentation.seachVacancyVm.SearchVacancyViewModel
import com.example.recruiterhunter.presentation.seachVacancyVm.intents.SearchScreenSideEffects
import com.example.recruiterhunter.ui.components.search_bar.SearchBarDock
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
    sideEffect?.let { effect ->
        when (effect) {
//            is JobListEffect.NavigateToJobDetail -> {
//                navController.navigate("jobDetails/${effect.jobId}")
//            }
            SearchScreenSideEffects.DownloadError -> LaunchedEffect(Unit) {

            }

            is SearchScreenSideEffects.OpenDetails -> TODO()
            SearchScreenSideEffects.OpenFilters -> TODO()
        }
    }
    LaunchedEffect(sideEffect) {
        when {
            sideEffect == SearchScreenSideEffects.DownloadError -> {
                snackBarHostState.showSnackbar(
                    message = (errorString)
                )
            }
        }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("asd")
                },
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPaddings ->
        Column(modifier = Modifier.padding(innerPaddings)) {
            SearchBarDock(
                state = viewModel.textField,
                labelIcon = searchIcon,
                filterIcon = filterIcon,
                onSearch = {}
            )
        }
    }
}