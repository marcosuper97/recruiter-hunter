package com.example.recruiterhunter.presentation.seachVacancyVm

import android.util.Log
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruiterhunter.domain.interactor.filters.FiltersInteractor
import com.example.recruiterhunter.presentation.seachVacancyVm.intents.SearchScreenIntent
import com.example.recruiterhunter.presentation.seachVacancyVm.intents.SearchScreenSideEffects
import domain.interactor.VacancySearchInteractor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SearchVacancyViewModel(
    private val vacancySearchInteractor: VacancySearchInteractor,
    private val filtersInteractor: FiltersInteractor
) :
    ViewModel() {
    val textField = TextFieldState("")

    private val _sideEffect = Channel<SearchScreenSideEffects>(Channel.BUFFERED)
    val sideEffect = _sideEffect.receiveAsFlow()

    private val _screenState: MutableState<SearchVacancyState> =
        mutableStateOf(SearchVacancyState())
    val screenState: State<SearchVacancyState> get() = _screenState

    private var canLoadMore: Boolean = false
    private var nextPage: Int = 0

    init {
        viewModelScope.launch {
            checkFilters()
        }
    }

    fun sendIntent(intent: SearchScreenIntent) {
        viewModelScope.launch {
            when (intent) {
                is SearchScreenIntent.DoNewRequest -> doNewRequest()
                is SearchScreenIntent.LoadNextPage -> loadNextPage()
            }
        }
    }

    fun sendSideEffect(effect: SearchScreenSideEffects) {
        viewModelScope.launch {
            when (effect) {
                is SearchScreenSideEffects.OpenDetails -> {}
                SearchScreenSideEffects.OpenFilters -> {}
                SearchScreenSideEffects.DownloadError -> _sideEffect.send(effect)
            }
        }
    }

    private fun loadNextPage() {
        if (canLoadMore) {
            viewModelScope.launch {
                _screenState.value = _screenState.value.copy(
                    loadingNextPage = true
                )
                val query = textField.text.toString()
                vacancySearchInteractor.doRequest(query, nextPage)
                    .onSuccess { (page, pages, found, vacancyList) ->
                        _screenState.value = _screenState.value.copy(
                            loadingNextPage = false,
                            vacancyList = _screenState.value.vacancyList + vacancyList,
                            vacanciesFounded = found
                        )
                        canLoadMore = page < pages
                        if (canLoadMore) nextPage = page + 1
                    }
                    .onFailure {
                        _screenState.value = _screenState.value.copy(
                            loadingNextPage = false
                        )
                        sendSideEffect(SearchScreenSideEffects.DownloadError)
                    }
            }
        }
    }

    private suspend fun checkFilters() {
        filtersInteractor.hasAnyFilters().collect { filtersState ->
            _screenState.value = _screenState.value.copy(
                hasAnyFilters = filtersState
            )
        }
    }

    private suspend fun doNewRequest() {
        _screenState.value = _screenState.value.copy(
            loading = true,
            loadingNextPage = false,
            error = false,
            hasContent = false,
            errorMessage = "",
        )
        Log.d("на загрузку", _screenState.value.toString())
        val query = textField.text.toString()
        vacancySearchInteractor.doRequest(query, 1)
            .onSuccess { (page, pages, found, vacancyList) ->
                _screenState.value = _screenState.value.copy(
                    loading = false,
                    loadingNextPage = false,
                    hasContent = true,
                    vacancyList = vacancyList,
                    vacanciesFounded = found
                )
                Log.d("SUCCESS", vacancyList.toString())
                canLoadMore = page < pages
                if (canLoadMore) nextPage = page + 1
            }
            .onFailure { failure ->
                _screenState.value = _screenState.value
                    .copy(
                        loading = false,
                        error = true,
                        hasContent = false,
                        errorMessage = failure.message,
                        vacancyList = emptyList()
                    )
                Log.d("провал", _screenState.value.toString())
            }
    }
}