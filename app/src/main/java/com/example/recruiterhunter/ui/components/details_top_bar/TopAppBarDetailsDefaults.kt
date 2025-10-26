package com.example.recruiterhunter.ui.components.details_top_bar

import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.dropShadow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.util.lerp
import androidx.navigation.NavController
import com.example.recruiterhunter.R
import com.example.recruiterhunter.ui.components.employer_logo.EmployerLogo
import com.example.recruiterhunter.ui.theme.vacancyDetailsTypo
import com.example.recruiterhunter.ui.transition_keys.DetailsTransition

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsTopBar(
    modifier: Modifier = Modifier,
    vacancyId: Long,
    vacancyName: String,
    employerName: String?,
    employerLogo: String? = null,
    address: String? = null,
    salary: String? = null,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    navController: NavController,
    appBarHeight: (Int) -> Unit,
    collapseProgress: () -> Float
) {
    val density = LocalDensity.current
    var maxHeightInt by remember { mutableIntStateOf(0) }
    var maxWidthInt by remember { mutableIntStateOf(0) }
    var boxIsMeasured by remember(maxHeightInt) { mutableStateOf(false) }

    val topBarAnimHolder = topBarAnimHolder(
        density = density,
        maxHeight = maxHeightInt,
        maxWidth = maxWidthInt,
        minHeight = TopAppBarDetailsDefaults.COLLAPSED,
        collapseProgress = collapseProgress(),
    )
    LaunchedEffect(collapseProgress) {
        Log.d(
            "высота контейнера", topBarAnimHolder.animatedTopBarBox.value.toString()
        )
        Log.d(
            "скругления краев", topBarAnimHolder.animatedRoundedCorner.value.toString()
        )
        Log.d(
            "смещение лого", topBarAnimHolder.employerLogoOffset.value.toString()
        )
        Log.d(
            "альфа текста", topBarAnimHolder.textAlphaAnim.value.toString()
        )
        Log.d(
            "скролл прогресс", collapseProgress().toString()
        )
    }

    val themeColors = MaterialTheme.colorScheme
    var topBackgroundColor by remember {
        mutableStateOf(themeColors.onSurface)
    }
    var elementsColor by remember {
        mutableStateOf(themeColors.onSurfaceVariant)
    }
    val employerLogoIsEmpty by remember { mutableStateOf(employerLogo != "") }
    val logoShape = CircleShape
    val locationIcon = ImageVector.vectorResource(R.drawable.outline_location_on_24)
    val paddingTop = LocalView.current.paddingTop.dp + 42.dp
    with(sharedTransitionScope) {
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(height = topBarAnimHolder.animatedTopBarBox.value)
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(
                        bottomEnd = topBarAnimHolder.animatedRoundedCorner.value,
                        bottomStart = topBarAnimHolder.animatedRoundedCorner.value,
                    ),
                    clip = false,
                    ambientColor = Color.Black,
                    spotColor = Color.Black
                )
                .background(
                    themeColors.surfaceContainer,
                    shape = RoundedCornerShape(
                        bottomEnd = topBarAnimHolder.animatedRoundedCorner.value,
                        bottomStart = topBarAnimHolder.animatedRoundedCorner.value
                    )
                )
                .onGloballyPositioned { coordinates ->
                    val newHeight = coordinates.size.height
                    val newWidth = coordinates.size.width
                    if (!isTransitionActive && !boxIsMeasured) {
                        appBarHeight(newHeight)
                        maxHeightInt = newHeight
                        maxWidthInt = newWidth
                        boxIsMeasured = true
                    }
                }
                .sharedBounds(
                    rememberSharedContentState(key = DetailsTransition.containerKey(vacancyId)),
                    animatedVisibilityScope = animatedVisibilityScope,
                    resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds,
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = topBackgroundColor.getBackGradient(),
                            startY = 0f,
                            endY = Float.POSITIVE_INFINITY
                        )
                    ),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.padding(top = paddingTop))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_back_24),
                        tint = elementsColor,
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                enabled = true,
                                onClick = {
                                    if (!sharedTransitionScope.isTransitionActive) {
                                        navController.popBackStack()
                                    }
                                },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            )
                    )

                    Spacer(modifier = Modifier.weight(1f))

                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.baseline_share_24),
                        tint = elementsColor,
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                enabled = true,
                                onClick = {
                                    if (!sharedTransitionScope.isTransitionActive) {
                                        navController.popBackStack()
                                    }
                                },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            )
                    )

                    Spacer(Modifier.padding(horizontal = 12.dp))

                    Icon(
                        imageVector = ImageVector.vectorResource(R.drawable.outline_bookmark_add_24),
                        tint = elementsColor,
                        contentDescription = "",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable(
                                enabled = true,
                                onClick = {
                                    if (!sharedTransitionScope.isTransitionActive) {
                                        navController.popBackStack()
                                    }
                                },
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                            )
                    )


                }
                Box(contentAlignment = Alignment.BottomCenter) {
                    if (employerLogoIsEmpty == true) {
                        EmployerLogo(
                            modifier = Modifier
                                .offset(x = topBarAnimHolder.employerLogoOffset.value)
                                .padding(top = 12.dp)
                                .dropShadow(
                                    shape = CircleShape
                                ) {
                                    offset = Offset(x = 0f, y = 8f)
                                    spread = 2f
                                    radius = 18f
                                    alpha = 0.4f
                                    color = topBackgroundColor
                                }
                                .background(
                                    brush = Brush.verticalGradient(
                                        colors = topBackgroundColor.getLogoGradient(),
                                        startY = 0f,
                                        endY = Float.POSITIVE_INFINITY
                                    ),
                                    shape = logoShape
                                )
                                .border(4.dp, topBackgroundColor, logoShape)
                                .size(topBarAnimHolder.employerLogoSizeAnim.value)
                                .clip(logoShape),
                            vacancyId = vacancyId,
                            employerLogo = employerLogo,
                            generatedBackgroundColors = { backgroundColor, elementColors ->
                                topBackgroundColor = backgroundColor
                                elementsColor = elementColors
                            }
                        )
                    }
                    Spacer(Modifier.padding(vertical = 12.dp))
                    Text(
                        text = vacancyName,
                        textAlign = TextAlign.Center,
                        style = vacancyDetailsTypo().cardVacancyNameStyle,
                        modifier = Modifier
                            .offset(
                                x = topBarAnimHolder.vacancyNameHorizontalAlignAnim.value,
                                y = topBarAnimHolder.vacancyNameVerticalAlignAnim.value
                            )
                            .sharedBounds(
                                rememberSharedContentState(DetailsTransition.vacancyName(vacancyId)),
                                animatedVisibilityScope = animatedVisibilityScope,
                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                            )
                    )
                }
                Spacer(Modifier.padding(vertical = 2.dp))
                Text(
                    text = employerName ?: "",
                    textAlign = TextAlign.Center,
                    style = vacancyDetailsTypo().vacancyEmployerStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .alpha(0.7f)
                        .sharedBounds(
                            rememberSharedContentState(DetailsTransition.employerName(vacancyId)),
                            animatedVisibilityScope = animatedVisibilityScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                        )
                )
                Spacer(Modifier.padding(vertical = 4.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 32.dp)
                ) {
                    Icon(
                        imageVector = locationIcon,
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                            .sharedElement(
                                rememberSharedContentState(
                                    DetailsTransition.navigationIcon(
                                        vacancyId
                                    )
                                ),
                                animatedVisibilityScope = animatedVisibilityScope
                            )
                    )
                    Text(
                        text = address ?: "",
                        textAlign = TextAlign.Center,
                        style = vacancyDetailsTypo().addressTextStyle,
                        modifier = Modifier
                            .sharedBounds(
                                rememberSharedContentState(DetailsTransition.address(vacancyId)),
                                animatedVisibilityScope = animatedVisibilityScope,
                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                            )
                    )
                }
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
                HorizontalDivider(Modifier.alpha(0.8f))
                Spacer(modifier = Modifier.padding(vertical = 6.dp))
                Text(
                    text = salary ?: "",
                    textAlign = TextAlign.Center,
                    style = vacancyDetailsTypo().salaryTextStyle,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp)
                        .sharedBounds(
                            rememberSharedContentState(DetailsTransition.salary(vacancyId)),
                            animatedVisibilityScope = animatedVisibilityScope,
                            resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                        )
                )
            }
        }
    }
}


@Stable
fun Int.convertToDp(density: Density): TopVacancyDetailsSize {
    val heightSize = this
    with(density) {
        return TopVacancyDetailsSize(
            convertedSize = heightSize.toDp(),
        )
    }
}

@Stable
fun Color.getBackGradient(): List<Color> = listOf(
    this.copy(1f),
    this.copy(0.8f),
    this.copy(0.6f),
    this.copy(0.2f),
    this.copy(0.06f),
    Color.Transparent,
    Color.Transparent,
    Color.Transparent,
    Color.Transparent,
)

@Stable
fun Color.getLogoGradient(): List<Color> = listOf(
    this.copy(0.16f),
    this.copy(0.08f),
    this.copy(0.02f),
)

@Immutable
data class TopVacancyDetailsSize(
    val convertedSize: Dp,
)

@Immutable
object TopAppBarDetailsDefaults {
    const val COLLAPSED: Int = 380
}

@Stable
@Composable
fun topBarAnimHolder(
    density: Density,
    maxHeight: Int,
    maxWidth: Int,
    minHeight: Int,
    collapseProgress: Float,
): TopBarAnimHolder {
    val maxHeightDp = remember(maxHeight, density) {
        if (maxHeight != 0) maxHeight.convertToDp(density).convertedSize else 0.dp
    }
    val minHeightDp = remember(minHeight, density) {
        minHeight.convertToDp(density).convertedSize
    }
    val maxWidthDp = remember(maxWidth, density) {
        maxWidth.convertToDp(density).convertedSize
    }

    val targetHeight = if (maxHeightDp != 0.dp) {
        lerp(maxHeightDp, minHeightDp, collapseProgress)
    } else Dp.Unspecified

    val animatedTopBarBox = animateDpAsState(targetHeight, label = "height")
    val animatedRoundedCorner = animateDpAsState(
        lerp(48.dp, 0.dp, collapseProgress),
        label = "corner"
    )
    val employerLogoAnim = animateDpAsState(
        lerp(0.dp, -(maxWidthDp / 2 - 48.dp), collapseProgress),
        label = "logo_offset"
    )
    val textAlphaAnim = animateFloatAsState(
        lerp(1f, 0f, collapseProgress),
        label = "alpha"
    )

    val employerLogoSizeAnim = animateDpAsState(
        lerp(100.dp, 48.dp, collapseProgress),
        label = "logo_size"
    )

    val vacancyNameVerticalAlignAnim = animateDpAsState(
        lerp(0.dp, (-18).dp, collapseProgress)
    )
    val vacancyNameHorizontalAlignAnim = animateDpAsState(
        lerp(0.dp, 18.dp, collapseProgress)
    )

    return remember {
        TopBarAnimHolder(
            animatedTopBarBox = animatedTopBarBox,
            animatedRoundedCorner = animatedRoundedCorner,
            employerLogoOffset = employerLogoAnim,
            employerLogoSizeAnim = employerLogoSizeAnim,
            textAlphaAnim = textAlphaAnim,
            vacancyNameHorizontalAlignAnim = vacancyNameHorizontalAlignAnim,
            vacancyNameVerticalAlignAnim = vacancyNameVerticalAlignAnim
        )
    }
}

@Immutable
data class TopBarAnimHolder(
    val animatedTopBarBox: State<Dp>,
    val animatedRoundedCorner: State<Dp>,
    val employerLogoOffset: State<Dp>,
    val employerLogoSizeAnim: State<Dp>,
    val textAlphaAnim: State<Float>,
    val vacancyNameHorizontalAlignAnim: State<Dp>,
    val vacancyNameVerticalAlignAnim: State<Dp>
)

