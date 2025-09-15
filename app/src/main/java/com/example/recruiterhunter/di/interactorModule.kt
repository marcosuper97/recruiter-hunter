package com.example.recruiterhunter.di

import domain.impl.VacancySearchInteractorImpl
import domain.interactor.VacancySearchInteractor
import org.koin.dsl.module

val interactorModule = module {
    single<VacancySearchInteractor> {
        VacancySearchInteractorImpl(get())
    }
}