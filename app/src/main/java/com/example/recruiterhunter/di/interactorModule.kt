package com.example.recruiterhunter.di

import com.example.recruiterhunter.domain.impl.cse.posts.PostsSearcherInteractorImpl
import com.example.recruiterhunter.domain.impl.cse.rectuiters.RecruitersSearchInteractorImpl
import com.example.recruiterhunter.domain.impl.favorites.control.FavoritesControlInteractorImpl
import com.example.recruiterhunter.domain.impl.favorites.fetch.FavoritesFetchInteractorImpl
import com.example.recruiterhunter.domain.impl.filters.FiltersInteractorImpl
import com.example.recruiterhunter.domain.impl.settings.SettingsInteractorImpl
import com.example.recruiterhunter.domain.impl.vacancy.VacancyDetailsInteractorImpl
import com.example.recruiterhunter.domain.interactor.cse.posts.PostsSearcherInteractor
import com.example.recruiterhunter.domain.interactor.cse.recruiters.RecruitersSearchInteractor
import com.example.recruiterhunter.domain.interactor.favorites.control.FavoritesControlInteractor
import com.example.recruiterhunter.domain.interactor.favorites.fetch.FavoritesFetchInteractor
import com.example.recruiterhunter.domain.interactor.filters.FiltersInteractor
import com.example.recruiterhunter.domain.interactor.settings.SettingsInteractor
import com.example.recruiterhunter.domain.interactor.vacancy.VacancyDetailsInteractor
import com.example.recruiterhunter.domain.interactor.vacancy.VacancySearchInteractor
import domain.impl.VacancySearchInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<VacancySearchInteractor> {
        VacancySearchInteractorImpl(get())
    }

    single<VacancyDetailsInteractor> {
        VacancyDetailsInteractorImpl(get())
    }

    single<RecruitersSearchInteractor> {
        RecruitersSearchInteractorImpl(get())
    }

    single<FiltersInteractor> {
        FiltersInteractorImpl(get(), get())
    }

    single<PostsSearcherInteractor> {
        PostsSearcherInteractorImpl(get())
    }

    single<FavoritesControlInteractor> {
        FavoritesControlInteractorImpl(get())
    }

    single<FavoritesFetchInteractor> {
        FavoritesFetchInteractorImpl(get())
    }

    single<SettingsInteractor> {
        SettingsInteractorImpl(
            get(),
            get(),
            get(),
            get()
        )
    }
}