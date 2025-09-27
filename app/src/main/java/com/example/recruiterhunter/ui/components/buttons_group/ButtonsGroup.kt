package com.example.recruiterhunter.ui.components.buttons_group

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recruiterhunter.R
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.ui.theme.RecruiterHunterTheme


data class ThemeButtonConfig(
    val text: String,
    val theme: ActualTheme,
    val iconInactive: Int,
    val iconActive: Int,
    val contentDescription: String,
    val clickAction: () -> Unit
)

@Composable
fun ThemeButtonGroups(
    actualTheme: ActualTheme,
    buttonsSet: List<ThemeButtonConfig>,
    modifier: Modifier = Modifier
) {
    Row {
        buttonsSet.forEachIndexed { index, (text, theme, iconInactive, iconActive, contentDescription, clickAction) ->
            val isActive = actualTheme.name == theme.name
            val iconRotation by animateFloatAsState(
                targetValue = if (isActive) 60f else 0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )

            val cornerRadius by animateDpAsState(
                targetValue = if (isActive) 24.dp else 6.dp,
                label = "ShapeChange",
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioHighBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )

            val elevatedAnimation by animateDpAsState(
                targetValue = if (isActive) 14.dp else 2.dp,
                label = "ButtonElevation",
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )

            val roundedShape = when (index) {
                0 -> RoundedCornerShape(
                    topStart = cornerRadius.coerceAtLeast(0.dp),
                    bottomStart = cornerRadius.coerceAtLeast(0.dp),
                    topEnd = 6.dp.coerceAtLeast(0.dp),
                    bottomEnd = 6.dp.coerceAtLeast(0.dp),
                )

                buttonsSet.lastIndex -> RoundedCornerShape(
                    topEnd = cornerRadius.coerceAtLeast(0.dp),
                    bottomEnd = cornerRadius.coerceAtLeast(0.dp),
                    topStart = 6.dp.coerceAtLeast(0.dp),
                    bottomStart = 6.dp.coerceAtLeast(0.dp)
                )

                else -> RoundedCornerShape(cornerRadius.coerceAtLeast(0.dp))
            }

            ElevatedButton(
                elevation = ButtonDefaults.elevatedButtonElevation(elevatedAnimation),
                onClick = { if (!isActive) clickAction() },
                shape = roundedShape,
                modifier = Modifier
                    .padding(2.dp)
                    .weight(1f)
                    .height(62.dp),
                contentPadding = PaddingValues(horizontal = 8.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AnimatedContent(targetState = isActive, transitionSpec = {
                        fadeIn().togetherWith(fadeOut())
                    }) { active ->
                        Icon(
                            painterResource(if (active) iconActive else iconInactive),
                            contentDescription = contentDescription,
                            modifier = Modifier
                                .graphicsLayer(rotationZ = iconRotation)
                                .size(20.dp)
                        )
                    }
                    Text(
                        text = text,
                        style = MaterialTheme.typography.titleSmall,
                        maxLines = 1,
                        fontWeight = if (isActive) FontWeight.Bold else FontWeight.Normal,
                        modifier = Modifier
                            .padding(start = 4.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSystem() {
    val buttonsMap =
        listOf(
            ThemeButtonConfig(
                text = stringResource(R.string.system_theme),
                theme = ActualTheme.SYSTEM,
                iconInactive = R.drawable.settings_inactive,
                iconActive = R.drawable.settings_active,
                contentDescription = stringResource(R.string.system_theme),
                {}
            ),
            ThemeButtonConfig(
                text = stringResource(R.string.dark_theme),
                theme = ActualTheme.DARK,
                iconInactive = R.drawable.moon_inactive,
                iconActive = R.drawable.moon_night,
                contentDescription = stringResource(R.string.dark_theme),
                {}
            ),
            ThemeButtonConfig(
                text = stringResource(R.string.light_theme),
                theme = ActualTheme.LIGHT,
                iconInactive = R.drawable.sun_inactive,
                iconActive = R.drawable.sun_active,
                contentDescription = stringResource(R.string.light_theme),
                {}
            )
        )
    var actualTheme by remember { mutableStateOf(ActualTheme.SYSTEM) }
    RecruiterHunterTheme(actualTheme) {
        ThemeButtonGroups(actualTheme = actualTheme, buttonsMap)
    }
}