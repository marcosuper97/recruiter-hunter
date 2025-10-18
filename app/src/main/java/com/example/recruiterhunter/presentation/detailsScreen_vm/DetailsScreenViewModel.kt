package com.example.recruiterhunter.presentation.detailsScreen_vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.recruiterhunter.domain.interactor.vacancy.VacancyDetailsInteractor
import com.example.recruiterhunter.ui.screens.details_screen.VacancyDetailsState

class DetailsScreenViewModel(
    private val vacancyDetailsInteractor: VacancyDetailsInteractor
) : ViewModel() {
    private val _screenState: MutableState<VacancyDetailsState> =
        mutableStateOf(VacancyDetailsState())
    val screenState: State<VacancyDetailsState> get() = _screenState

    init {

    }
}