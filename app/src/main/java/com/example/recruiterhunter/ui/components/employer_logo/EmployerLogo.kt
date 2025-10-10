package com.example.recruiterhunter.ui.components.employer_logo

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recruiterhunter.ui.transition_keys.DetailsTransition
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun EmployerLogo(
    vacancyId: Long,
    employerLogo: String?,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    generatedBackgroundColors: ((background: Color, foreground: Color) -> Unit)? = null,
) {
    val scope = rememberCoroutineScope()
    with(sharedTransitionScope) {
        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(4.dp))
                .sharedElement(
                    sharedContentState = rememberSharedContentState(
                        key = DetailsTransition.vacancyIdKey(
                            vacancyId
                        )
                    ),
                    animatedVisibilityScope = animatedVisibilityScope
                ),
            model = buildImageRequest(employerLogo, vacancyId),
            contentScale = ContentScale.Fit,
            contentDescription = "",
            onSuccess = { state ->
                generatedBackgroundColors?.let { callback ->
                    scope.launch(Dispatchers.Default) {
                        val image = state.result.drawable.toBitmap()
                        val palette = Palette.from(image).generate()

                        palette.dominantSwatch?.let { swatch ->
                            withContext(Dispatchers.Main) {
                                callback(
                                    Color(swatch.rgb),
                                    Color(swatch.titleTextColor)
                                )
                            }
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun buildImageRequest(employerLogo: String?, vacancyId: Long): ImageRequest {
    val context = LocalContext.current
    return remember(employerLogo, vacancyId, context) {
        ImageRequest.Builder(context)
            .data(employerLogo)
            .memoryCacheKey("vacancy_logo${vacancyId}")
            .placeholderMemoryCacheKey("vacancy_logo${vacancyId}")
            .allowHardware(false)
            .build()
    }
}