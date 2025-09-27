package com.example.recruiterhunter.ui.components.buttons_group

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recruiterhunter.R
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.ui.theme.RecruiterHunterTheme
import kotlin.system.exitProcess

data class SettingButtonsConfig(
    val text: String,
    val icon: Int,
    val action: () -> Unit
)

@Composable
private fun SettingsButton(
    text: String,
    icon: Int,
    dividerIsEnabled: Boolean = true,
    shape: RoundedCornerShape = RoundedCornerShape(0.dp),
    action: () -> Unit,
) {
    Box(
        modifier = Modifier
            .clip(shape)
            .background(MaterialTheme.colorScheme.surfaceContainerLow)
            .clickable(
                onClick = action
            )
    ) {
        Column(
            Modifier
                .height(62.dp)
                .padding(horizontal = 16.dp)
        ) {
            if (dividerIsEnabled) HorizontalDivider(
                modifier = Modifier.alpha(
                    0.3f
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxHeight()
            ) {
                Text(
                    text = text,
                    modifier = Modifier
                        .weight(1f),
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Icon(
                    painterResource(icon), contentDescription = text,
                    modifier = Modifier.padding(end = 4.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
fun SettingsButtonsGroup(
    buttonsParams: List<SettingButtonsConfig>
) {
    Column(modifier = Modifier.shadow(2.dp, RoundedCornerShape(24.dp))) {
        buttonsParams.forEachIndexed { index, (text, icon, action) ->
            val buttonShape = when {
                buttonsParams.size == 1 -> RoundedCornerShape(24.dp)
                index == 0 -> RoundedCornerShape(
                    topStart = 24.dp,
                    topEnd = 24.dp,
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                )

                index == buttonsParams.lastIndex -> RoundedCornerShape(
                    topStart = 0.dp,
                    topEnd = 0.dp,
                    bottomStart = 24.dp,
                    bottomEnd = 24.dp
                )

                else -> {
                    RoundedCornerShape(0.dp)
                }
            }
            val dividerIsEnable = index != 0

            SettingsButton(
                text = text,
                icon = icon,
                dividerIsEnabled = dividerIsEnable,
                shape = buttonShape,
                action = action
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ButtonSettingPreview() {
    RecruiterHunterTheme(ActualTheme.DARK) {
        val buttonsList: List<SettingButtonsConfig> = listOf(
            SettingButtonsConfig(
                text = "Системные настройки",
                icon = R.drawable.settings_inactive,
                action = {}
            ),
            SettingButtonsConfig(
                text = "Обратная связь",
                icon = R.drawable.settings_inactive,
                action = {}
            ),
            SettingButtonsConfig(
                text = "Исходный код приложения",
                icon = R.drawable.settings_inactive,
                action = {}
            ),
            SettingButtonsConfig(
                text = "Что-то еще",
                icon = R.drawable.settings_inactive,
                action = { exitProcess(0) }
            ),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            SettingsButtonsGroup(
                buttonsParams = buttonsList
            )
        }
    }
}