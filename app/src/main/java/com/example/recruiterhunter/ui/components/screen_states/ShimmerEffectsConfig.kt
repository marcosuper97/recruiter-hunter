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
    progressProvider: () -> Float,
    containerWidthProvider: Float,
    containerHeightProvider: Float,
    baseColor: Color,
    shimmerColor: Color
): Modifier = this.drawWithCache {
    val progressShimmer = progressProvider()
    val shimmerWidth = containerWidthProvider * ShimmerEffectsConfig.SHIMMER_WIDTH
    val offset = containerWidthProvider * progressShimmer

    val brush = Brush.linearGradient(
        colors = listOf(baseColor, shimmerColor, baseColor),
        start = Offset(offset, 0f),
        end = Offset(offset + shimmerWidth, containerHeightProvider)
    )
    onDrawBehind {
        drawRect(brush = brush)
    }
}