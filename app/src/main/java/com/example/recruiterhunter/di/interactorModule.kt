package com.example.recruiterhunter.di

import com.example.recruiterhunter.domain.impl.favorites.FavoritesInteractorImpl
import com.example.recruiterhunter.domain.impl.filters.FiltersInteractorImpl
import com.example.recruiterhunter.domain.impl.vacancy.VacancyDetailsInteractorImpl
import com.example.recruiterhunter.domain.interactor.favorites.FavoritesInteractor
import com.example.recruiterhunter.domain.interactor.filters.FiltersInteractor
import com.example.recruiterhunter.domain.interactor.vacancy.VacancyDetailsInteractor
import domain.impl.VacancySearchInteractorImpl
import domain.interactor.VacancySearchInteractor
import org.koin.dsl.module

val interactorModule = module {
    single<VacancySearchInteractor> {
        VacancySearchInteractorImpl(get())
    }

    single<VacancyDetailsInteractor> {
        VacancyDetailsInteractorImpl(get())
    }

    single<FiltersInteractor> {
        FiltersInteractorImpl(get(), get())
    }

    single<FavoritesInteractor> {
        FavoritesInteractorImpl(get())
    }
}