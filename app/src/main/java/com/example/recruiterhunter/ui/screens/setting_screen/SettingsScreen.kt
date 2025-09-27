package com.example.recruiterhunter.ui.screens.setting_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.recruiterhunter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen() {
    val context = LocalContext.current
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(stringResource(R.string.app_settings)) },
            modifier = TODO(),
            navigationIcon = TODO(),
            actions = TODO(),
            expandedHeight = TODO(),
            windowInsets = TODO(),
            colors = TODO(),
            scrollBehavior = TODO()
        )
    }) {


    }
}