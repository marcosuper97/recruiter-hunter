package com.example.recruiterhunter.ui.components.screen_states

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

object ShimmerEffectsConfig {
    const val SHIMMER_START = -1f + 0.4f
    const val SHIMMER_END = 1f
    const val SHIMMER_WIDTH = 0.4f
    const val SHIMMER_ANIM_DURATION = 1500
}

fun Modifier.drawShimmer(
    progress: Float,
    mainContainerHeight: Float,
    mainContainerWidth: Float,
    baseColor: Color,
    shimmerColor: Color
): Modifier = this.drawWithCache {
    val shimmerWidth = mainContainerWidth * ShimmerEffectsConfig.SHIMMER_WIDTH
    val offset = mainContainerWidth * progress

    val brush = Brush.linearGradient(
        colors = listOf(baseColor, shimmerColor, baseColor),
        start = Offset(offset, 0f),
        end = Offset(offset + shimmerWidth, mainContainerHeight)
    )
    onDrawBehind {
        drawRect(brush = brush)
    }
}