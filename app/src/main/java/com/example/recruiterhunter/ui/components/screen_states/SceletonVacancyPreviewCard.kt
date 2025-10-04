package com.example.recruiterhunter.ui.components.screen_states

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.ui.theme.RecruiterHunterTheme

@Composable
fun SkeletonVacancyPreviewCard(
    modifier: Modifier = Modifier
) {
    val shimmerProgress by rememberInfiniteTransition().animateFloat(
        initialValue = ShimmerEffectsConfig.SHIMMER_START,
        targetValue = ShimmerEffectsConfig.SHIMMER_END,
        animationSpec = infiniteRepeatable(
            animation = tween(
                ShimmerEffectsConfig.SHIMMER_ANIM_DURATION,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_progress"
    )
    var containerWidth by remember { mutableFloatStateOf(0f) }
    var containerHeight by remember { mutableFloatStateOf(0f) }

    val bgColor = MaterialTheme.colorScheme.surfaceVariant
    val shimmerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)

    val elementCornerShape = remember { RoundedCornerShape(4.dp) }

    Column(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(CardDefaults.cardColors().containerColor)
            .padding(12.dp)
            .alpha(0.4f)
            .onSizeChanged { size ->
                val newWidth = size.width.toFloat()
                val newHeight = size.height.toFloat()
                if (containerWidth != newWidth) containerWidth = newWidth
                if (containerHeight != newHeight) containerHeight = newHeight
            }
    ) {
        Row(verticalAlignment = Alignment.Top) {
            Box(
                Modifier
                    .size(56.dp)
                    .clip(elementCornerShape)
                    .drawShimmerOptimized(
                        { shimmerProgress },
                        containerHeightProvider = containerHeight,
                        containerWidthProvider = containerWidth,
                        baseColor = bgColor,
                        shimmerColor = shimmerColor
                    )
            )
            Spacer(modifier = Modifier.width(6.dp))
            Column {
                Box(
                    Modifier
                        .width(100.dp)
                        .height(18.dp)
                        .clip(elementCornerShape)
                        .drawShimmerOptimized(
                            { shimmerProgress },
                            containerHeightProvider = containerHeight,
                            containerWidthProvider = containerWidth,
                            baseColor = bgColor,
                            shimmerColor = shimmerColor
                        )
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    Modifier
                        .width(70.dp)
                        .height(13.dp)
                        .clip(elementCornerShape)
                        .drawShimmerOptimized(
                            { shimmerProgress },
                            containerHeightProvider = containerHeight,
                            containerWidthProvider = containerWidth,
                            baseColor = bgColor,
                            shimmerColor = shimmerColor
                        )
                )
            }
        }
        Spacer(modifier = Modifier.height(6.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                Modifier
                    .size(20.dp)
                    .clip(elementCornerShape)
                    .drawShimmerOptimized(
                        { shimmerProgress },
                        containerHeightProvider = containerHeight,
                        containerWidthProvider = containerWidth,
                        baseColor = bgColor,
                        shimmerColor = shimmerColor
                    )
            )
            Spacer(modifier = Modifier.width(2.dp))
            Box(
                Modifier
                    .width(80.dp)
                    .height(14.dp)
                    .clip(elementCornerShape)
                    .drawShimmerOptimized(
                        { shimmerProgress },
                        containerHeightProvider = containerHeight,
                        containerWidthProvider = containerWidth,
                        baseColor = bgColor,
                        shimmerColor = shimmerColor
                    )
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
        Box(
            Modifier
                .fillMaxWidth()
                .height(1.dp)
                .drawShimmerOptimized(
                    { shimmerProgress },
                    containerHeightProvider = containerHeight,
                    containerWidthProvider = containerWidth,
                    baseColor = bgColor,
                    shimmerColor = shimmerColor
                )
        )
        Spacer(modifier = Modifier.height(6.dp))
        Row {
            Box(
                Modifier
                    .width(90.dp)
                    .height(17.dp)
                    .clip(elementCornerShape)
                    .drawShimmerOptimized(
                        { shimmerProgress },
                        containerHeightProvider = containerHeight,
                        containerWidthProvider = containerWidth,
                        baseColor = bgColor,
                        shimmerColor = shimmerColor
                    )
            )
        }
    }
}


@Preview
@Composable
fun PreviewSkeleton() {
    RecruiterHunterTheme(ActualTheme.DARK) {
        Scaffold { innerPadding ->
            Column(modifier = Modifier.padding(innerPadding)) {
                SkeletonVacancyPreviewCard()
            }
        }
    }
}