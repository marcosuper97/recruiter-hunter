package com.example.recruiterhunter

import android.app.Application
import com.example.recruiterhunter.di.dataModule
import com.example.recruiterhunter.di.interactorModule
import com.example.recruiterhunter.di.repositoryModule
import com.example.recruiterhunter.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(dataModule, repositoryModule, interactorModule, viewModelModule)
        }
    }
}