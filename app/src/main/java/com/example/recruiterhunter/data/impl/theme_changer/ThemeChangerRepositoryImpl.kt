package com.example.recruiterhunter.data.impl.theme_changer

import com.example.recruiterhunter.data.local.data_store.ThemeDataStore
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.domain.repository.theme_changer.ThemeChangerRepository
import kotlinx.coroutines.flow.Flow

class ThemeChangerRepositoryImpl(private val dataStore: ThemeDataStore) : ThemeChangerRepository {
    override fun getTheme(): Flow<ActualTheme> = dataStore.getTheme()

    override suspend fun changeTheme(theme: ActualTheme) {
        dataStore.setTheme(theme)
    }
}