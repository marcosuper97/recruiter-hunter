package com.example.recruiterhunter.data.impl.theme_changer

import com.example.recruiterhunter.core.theme_changer.state.ActualTheme
import com.example.recruiterhunter.domain.repository.theme_changer.ThemeChangerRepository
import kotlinx.coroutines.flow.Flow

class ThemeChangerRepositoryImpl(): ThemeChangerRepository {
    override fun getTheme(): Flow<ActualTheme> {
        TODO("Not yet implemented")
    }

    override suspend fun changeDark(theme: ActualTheme) {
        TODO("Not yet implemented")
    }
}