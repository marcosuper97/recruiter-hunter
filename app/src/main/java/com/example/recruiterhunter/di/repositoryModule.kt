package com.example.recruiterhunter.di

import com.example.recruiterhunter.data.impl.VacancySearchRepositoryImpl
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
}