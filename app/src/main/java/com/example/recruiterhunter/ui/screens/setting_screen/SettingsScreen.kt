package com.example.recruiterhunter.ui.screens.setting_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.recruiterhunter.R
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.presentation.settings_vm.SettingsIntent
import com.example.recruiterhunter.presentation.settings_vm.SettingsScreenViewModel
import com.example.recruiterhunter.ui.components.buttons_group.SettingButtonsConfig
import com.example.recruiterhunter.ui.components.buttons_group.SettingsButtonsGroup
import com.example.recruiterhunter.ui.components.buttons_group.ThemeButtonConfig
import com.example.recruiterhunter.ui.components.buttons_group.ThemeButtonGroups
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    actualTheme: ActualTheme,
    viewModel: SettingsScreenViewModel = koinViewModel()
) {

    val themeButtons = listOf(
        ThemeButtonConfig(
            text = stringResource(R.string.system_theme),
            theme = ActualTheme.SYSTEM,
            iconInactive = R.drawable.settings_inactive,
            iconActive = R.drawable.settings_active,
            contentDescription = stringResource(R.string.system_theme)
        ) { viewModel.sendIntent(SettingsIntent.SetSystemTheme()) },

        ThemeButtonConfig(
            text = stringResource(R.string.dark_theme),
            theme = ActualTheme.DARK,
            iconInactive = R.drawable.moon_inactive,
            iconActive = R.drawable.moon_night,
            contentDescription = stringResource(R.string.dark_theme),
        ) { viewModel.sendIntent(SettingsIntent.SetDarkTheme()) },

        ThemeButtonConfig(
            text = stringResource(R.string.light_theme),
            theme = ActualTheme.LIGHT,
            iconInactive = R.drawable.sun_inactive,
            iconActive = R.drawable.sun_active,
            contentDescription = stringResource(R.string.light_theme)
        ) { viewModel.sendIntent(SettingsIntent.SetLightTheme()) }
    )

    val settingsButtons = listOf(
        SettingButtonsConfig(
            text = stringResource(R.string.open_system_settings),
            icon = R.drawable.settings_inactive,
        ) { viewModel.sendIntent(SettingsIntent.OpenSettings) },
        SettingButtonsConfig(
            text = stringResource(R.string.feedback),
            icon = R.drawable.outline_mail_24
        ) { viewModel.sendIntent(SettingsIntent.SendFeedBack) },
        SettingButtonsConfig(
            text = stringResource(R.string.open_source_github),
            icon = R.drawable.icon_github,
        ) { viewModel.sendIntent(SettingsIntent.OpenSourceCode) },
    )

    Scaffold(topBar = {
        key(actualTheme) {
            TopAppBar(
                title = { Text(stringResource(R.string.app_settings)) },
                navigationIcon = {
                    Icon(
                        painterResource(R.drawable.baseline_arrow_back_24),
                        contentDescription = stringResource(R.string.button_go_back)
                    )
                },
                actions = {},
                modifier = Modifier.padding(start = 12.dp, end = 8.dp)
            )
        }
    }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 12.dp)
        ) {

            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            ThemeButtonGroups(
                actualTheme,
                themeButtons
            )
            Spacer(modifier = Modifier.padding(vertical = 8.dp))
            SettingsButtonsGroup(
                settingsButtons
            )
        }
    }
}