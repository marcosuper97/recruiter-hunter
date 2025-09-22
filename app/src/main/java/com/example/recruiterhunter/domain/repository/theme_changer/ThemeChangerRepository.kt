package com.example.recruiterhunter.domain.repository.theme_changer

interface ThemeChangerRepository {
    suspend fun changeLight()
    suspend fun changeDark()
    suspend fun changeSystem()
}