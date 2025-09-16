package com.example.recruiterhunter.di

import com.example.recruiterhunter.data.impl.filters.FiltersDbRepositoryImpl
import com.example.recruiterhunter.data.impl.filters.FiltersNetworkRepositoryImpl
import com.example.recruiterhunter.data.impl.vacancy.VacancyDetailsRepositoryImpl
import com.example.recruiterhunter.data.impl.vacancy.VacancySearchRepositoryImpl
import com.example.recruiterhunter.domain.repository.filters.FiltersDbRepository
import com.example.recruiterhunter.domain.repository.filters.FiltersNetworkRepository
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

    single<FiltersDbRepository> {
        FiltersDbRepositoryImpl(get(), get())
    }

    single<FiltersNetworkRepository> {
        FiltersNetworkRepositoryImpl(
            get(),
            get(),
            get()
        )
    }
}