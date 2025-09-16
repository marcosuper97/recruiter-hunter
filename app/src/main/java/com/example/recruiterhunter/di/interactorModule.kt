package com.example.recruiterhunter.di

import com.example.recruiterhunter.domain.impl.vacancy.VacancyDetailsInteractorImpl
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
}