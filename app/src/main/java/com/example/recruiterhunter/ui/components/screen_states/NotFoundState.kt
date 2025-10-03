package com.example.recruiterhunter.ui.components.screen_states

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recruiterhunter.R
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.ui.theme.RecruiterHunterTheme

@Composable
fun ErrorStateScreen(
    modifier: Modifier = Modifier,
    title: String? = null,
    message: String? = null,
    iconState: ImageVector? = null
) {
    val textStyle = MaterialTheme.typography
    val colors = MaterialTheme.colorScheme
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            iconState?.let {
                Icon(
                    imageVector = it,
                    tint = Color.Unspecified,
                    contentDescription = "",
                    modifier = Modifier.size(210.dp),
                )
            }
            title?.let {
                Text(
                    it,
                    style = textStyle.displaySmall,
                    textAlign = TextAlign.Center,
                    color = colors.primary,
                    fontWeight = FontWeight.Light
                )
            }
            message?.let {
                Text(
                    it,
                    style = textStyle.titleLarge,
                    textAlign = TextAlign.Center,
                    color = colors.primary,
                    fontWeight = FontWeight.Thin,
                    modifier = Modifier.alpha(0.4f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNotFoundScreen1() {
    RecruiterHunterTheme(ActualTheme.DARK) {
        Scaffold { innerPaddings ->
            ErrorStateScreen(
                modifier = Modifier.padding(innerPaddings),
                title = "Ошибка сети",
                message = "Свяжитесь с разработчиком приложения через \"настройки\" ",
                iconState = ImageVector.vectorResource(R.drawable.error_naughty_dog)
            )
        }
    }
}
