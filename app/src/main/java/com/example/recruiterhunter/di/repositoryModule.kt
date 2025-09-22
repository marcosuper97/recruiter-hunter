package com.example.recruiterhunter.di

import com.example.recruiterhunter.data.impl.cse.posts.PostsSearcherRepositoryImpl
import com.example.recruiterhunter.data.impl.cse.recriuters.RecruitersSearcherRepositoryImpl
import com.example.recruiterhunter.data.impl.favorites.FavoritesRepositoryImpl
import com.example.recruiterhunter.data.impl.filters.FiltersDbRepositoryImpl
import com.example.recruiterhunter.data.impl.filters.FiltersNetworkRepositoryImpl
import com.example.recruiterhunter.data.impl.theme_changer.ThemeChangerRepositoryImpl
import com.example.recruiterhunter.data.impl.vacancy.VacancyDetailsRepositoryImpl
import com.example.recruiterhunter.data.impl.vacancy.VacancySearchRepositoryImpl
import com.example.recruiterhunter.domain.repository.cse.posts.PostsSearcherRepository
import com.example.recruiterhunter.domain.repository.cse.recruiters.RecruitersSearcherRepository
import com.example.recruiterhunter.domain.repository.favorites.FavoritesRepository
import com.example.recruiterhunter.domain.repository.filters.FiltersDbRepository
import com.example.recruiterhunter.domain.repository.filters.FiltersNetworkRepository
import com.example.recruiterhunter.domain.repository.theme_changer.ThemeChangerRepository
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

    single<RecruitersSearcherRepository> {
        RecruitersSearcherRepositoryImpl(
            get(),
            get()
        )
    }

    single<PostsSearcherRepository> {
        PostsSearcherRepositoryImpl(
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

    single<FavoritesRepository> {
        FavoritesRepositoryImpl(get(), get())
    }

    single<ThemeChangerRepository>{
        ThemeChangerRepositoryImpl(get())
    }
}