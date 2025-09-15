package com.example.recruiterhunter.di

import com.example.recruiterhunter.data.impl.vacancy.VacancyDetailsRepositoryImpl
import com.example.recruiterhunter.data.impl.vacancy.VacancySearchRepositoryImpl
import domain.repository.VacancyDetailsRepository
import domain.repository.VacancySearchRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<VacancySearchRepository> {
        VacancySearchRepositoryImpl(
            get(),
            get(),
            get(),
            get()
        )
    }

    single<VacancyDetailsRepository> {
        VacancyDetailsRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
}