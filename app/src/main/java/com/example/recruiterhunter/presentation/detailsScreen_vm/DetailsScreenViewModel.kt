package com.example.recruiterhunter.presentation.detailsScreen_vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recruiterhunter.domain.actions.share.ShareAction
import com.example.recruiterhunter.domain.interactor.favorites.control.FavoritesControlInteractor
import com.example.recruiterhunter.domain.interactor.vacancy.VacancyDetailsInteractor
import com.example.recruiterhunter.ui.screens.details_screen.VacancyDetailsIntent
import com.example.recruiterhunter.ui.screens.details_screen.VacancyDetailsState
import kotlinx.coroutines.launch

class DetailsScreenViewModel(
    private val vacancyDetailsInteractor: VacancyDetailsInteractor,
    private val favoritesInteractor: FavoritesControlInteractor,
    private val shareAction: ShareAction
) : ViewModel() {
    private val _screenState: MutableState<VacancyDetailsState> =
        mutableStateOf(VacancyDetailsState())
    val screenState: State<VacancyDetailsState> get() = _screenState

    fun sendIntent(intent: VacancyDetailsIntent) {
        when (intent) {
            is VacancyDetailsIntent.FetchDetails -> fetchDetails(intent.vacancyId)
            VacancyDetailsIntent.MarkFavorite -> favoriteControl()
            VacancyDetailsIntent.ShareVacancy -> shareVacancy()
        }
    }

    private fun fetchDetails(vacancyId: Long) {
        viewModelScope.launch {
            vacancyDetailsInteractor.fetchDetails(vacancyId)
                .onSuccess { vacancyDetails ->
                    _screenState.value = _screenState.value.copy(
                        isLoading = false,
                        content = true,
                        vacancyDetails = vacancyDetails
                    )
                }
                .onFailure {
                    _screenState.value = _screenState.value.copy(
                        isLoading = false,
                        isError = true,
                    )
                }
        }
    }

    private fun favoriteControl() {
        viewModelScope.launch {
            screenState.value.vacancyDetails?.let { vacancy ->
                if (vacancy.isFavorite) {
                    favoritesInteractor.removeFromFavorites(vacancy.vacancyId)
                    _screenState.value =
                        _screenState.value.copy(vacancyDetails = vacancy.copy(isFavorite = false))
                } else {
                    favoritesInteractor.addToFavorites(vacancy)
                    _screenState.value =
                        _screenState.value.copy(vacancyDetails = vacancy.copy(isFavorite = true))
                }
            }
        }
    }

    private fun shareVacancy() {
        viewModelScope.launch {
            screenState.value.vacancyDetails?.let { vacancy ->
                shareAction.shareVacancy(vacancy.linkUrl)
            }
        }
    }
}