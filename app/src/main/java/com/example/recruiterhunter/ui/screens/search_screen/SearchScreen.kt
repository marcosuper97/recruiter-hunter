package com.example.recruiterhunter.ui.screens.search_screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.ui.theme.RecruiterHunterTheme

@Composable
fun SearchScreen() {
}


@Preview
@Composable
fun SearchScreenPreview() {
    RecruiterHunterTheme(ActualTheme.LIGHT) {
        SearchScreen()
    }
}