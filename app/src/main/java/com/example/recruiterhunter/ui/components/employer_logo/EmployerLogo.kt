package com.example.recruiterhunter.ui.components.employer_logo

import android.graphics.drawable.Drawable
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.recruiterhunter.R
import kotlinx.coroutines.CoroutineScope
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
                getPalette(
                    scope = scope,
                    drawable = state.result.drawable
                ) { topAppBackgroundColor, elementsColor ->
                    callback(
                        topAppBackgroundColor,
                        elementsColor
                    )
                }
            }
        }
    )
}

@Stable
@Composable
fun buildImageRequest(employerLogo: String?, vacancyId: Long): ImageRequest {
    val context = LocalContext.current
    return remember(employerLogo, vacancyId, context) {
        ImageRequest.Builder(context)
            .data(employerLogo)
            .memoryCacheKey("vacancy_logo${vacancyId}")
            .placeholderMemoryCacheKey("vacancy_logo${vacancyId}")
            .allowHardware(false)
            .error(R.drawable.image_placeholder)
            .build()
    }
}

@Stable
fun getPalette(
    scope: CoroutineScope,
    drawable: Drawable,
    generatedBackgroundColors: ((topAppBackgroundColor: Color, elementsColor: Color) -> Unit)
) {
    scope.launch(Dispatchers.Default) {
        val image = drawable.toBitmap()
        val palette = Palette.from(image).generate()
        palette.dominantSwatch?.let { swatch ->
            withContext(Dispatchers.Main) {
                generatedBackgroundColors(
                    Color(swatch.rgb),
                    Color(swatch.titleTextColor)
                )
            }
        }
    }
}