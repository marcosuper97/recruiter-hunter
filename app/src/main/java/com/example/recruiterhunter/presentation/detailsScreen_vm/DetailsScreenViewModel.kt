package com.example.recruiterhunter.presentation.detailsScreen_vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruiterhunter.domain.interactor.favorites.FavoritesInteractor
import com.example.recruiterhunter.domain.interactor.vacancy.VacancyDetailsInteractor
import com.example.recruiterhunter.ui.screens.details_screen.VacancyDetailsIntent
import com.example.recruiterhunter.ui.screens.details_screen.VacancyDetailsState
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val vacancyDetailsInteractor: VacancyDetailsInteractor,
    private val favoritesInteractor: FavoritesInteractor
) : ViewModel() {
    private val _screenState: MutableState<VacancyDetailsState> =
        mutableStateOf(VacancyDetailsState())
    val screenState: State<VacancyDetailsState> get() = _screenState

    fun sendIntent(intent: VacancyDetailsIntent) {
        when (intent) {
            is VacancyDetailsIntent.FetchDetails -> fetchDetails(intent.vacancyId)
            VacancyDetailsIntent.MarkFavorite -> TODO()
            VacancyDetailsIntent.ShareVacancy -> TODO()
        }
    }

    private fun fetchDetails(vacancyId: Long) {
        viewModelScope.launch {
            vacancyDetailsInteractor.fetchDetails(vacancyId)
                .onSuccess { vacancyDetails ->
                    _screenState.value = _screenState.value.copy(
                        isLoading = false,
                        isError = false,
                        content = true,
                        isFavorite = false,
                        vacancyDetails = vacancyDetails
                    )
                }
                .onFailure {
                    _screenState.value = _screenState.value.copy(
                        isLoading = false,
                        isError = true,
                        content = false,
                        isFavorite = false,
                    )
                }
        }
    }

    private fun favoriteControl() {
        val isFavorite = screenState.value.isFavorite

    }
}