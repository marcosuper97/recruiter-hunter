package com.example.recruiterhunter.di

import com.example.recruiterhunter.data.impl.cse.posts.PostsSearcherRepositoryImpl
import com.example.recruiterhunter.data.impl.cse.recriuters.RecruitersSearcherRepositoryImpl
import com.example.recruiterhunter.data.impl.favorites.control.FavoritesControlRepositoryImpl
import com.example.recruiterhunter.data.impl.favorites.fetch.FavoritesFetchRepositoryImpl
import com.example.recruiterhunter.data.impl.filters.FiltersDbRepositoryImpl
import com.example.recruiterhunter.data.impl.filters.FiltersNetworkRepositoryImpl
import com.example.recruiterhunter.data.impl.vacancy.VacancyDetailsRepositoryImpl
import com.example.recruiterhunter.data.impl.vacancy.VacancySearchRepositoryImpl
import com.example.recruiterhunter.domain.repository.cse.posts.PostsSearcherRepository
import com.example.recruiterhunter.domain.repository.cse.recruiters.RecruitersSearcherRepository
import com.example.recruiterhunter.domain.repository.favorites.control.FavoritesControlRepository
import com.example.recruiterhunter.domain.repository.favorites.fetch.FavoritesFetchRepository
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

    single<FavoritesFetchRepository> {
        FavoritesFetchRepositoryImpl(get(), get())
    }

    single<FavoritesControlRepository> {
        FavoritesControlRepositoryImpl(get(), get())
    }
}