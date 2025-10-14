package com.example.recruiterhunter.ui.components.employer_logo

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun EmployerLogo(
    modifier: Modifier = Modifier,
    vacancyId: Long,
    employerLogo: String?,
    generatedBackgroundColors: ((topAppBackgroundColor: Color, elementsColor: Color) -> Unit)? = null,
) {
    val scope = rememberCoroutineScope()
    AsyncImage(
        modifier = modifier,
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