package com.example.recruiterhunter.ui.components.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recruiterhunter.R
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.ui.theme.RecruiterHunterTheme

@Composable
fun ButtonSetting(text: String, icon: Int, action: () -> Unit) {
    Column(
        Modifier
            .height(62.dp)
            .clickable(
                true,
                onClick = {}
            )) {
        HorizontalDivider(modifier = Modifier.alpha(0.3f))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .weight(1f),
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                painterResource(icon), contentDescription = text,
                modifier = Modifier.padding(end = 8.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ButtonSettingPreview() {
    RecruiterHunterTheme(ActualTheme.DARK) {
        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
            ButtonSetting(
                "Системные настройки",
                icon = R.drawable.settings_active,
                action = {}
            )
            ButtonSetting(
                "Обратная связь",
                icon = R.drawable.settings_active,
                action = {}
            )
            ButtonSetting(
                "Исходный код приложения",
                icon = R.drawable.settings_active,
                action = {}
            )
            ButtonSetting(
                "Строка",
                icon = R.drawable.settings_active,
                action = {}
            )
        }
    }
}