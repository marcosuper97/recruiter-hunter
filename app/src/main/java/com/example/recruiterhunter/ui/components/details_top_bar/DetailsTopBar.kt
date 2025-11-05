package com.example.recruiterhunter.ui.components.details_top_bar

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
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
    appBarHeight: (Float) -> Unit,
    collapseProgress: () -> Float
) {
    val currentProgress = collapseProgress()
    val density = LocalDensity.current
    var maxHeightDp by remember { mutableStateOf(0.dp) }
    var vacancyNameHeight by remember { mutableStateOf(0.dp) }

    val topBarAnimHolder = topBarAnimHolder(
        maxHeightDp = maxHeightDp,
        collapseProgress = { currentProgress },
    )

    var logoSpaceWidth by remember { mutableStateOf(0.dp) }
    val logoSpaceAnimHolder = logoSpaceAnim(
        width = logoSpaceWidth,
        vacancyNameHeightDp = vacancyNameHeight,
        collapseProgress = { currentProgress }
    )

    val themeColors = MaterialTheme.colorScheme
    var topBackgroundColor by remember {
        mutableStateOf(themeColors.onSurface)
    }
    var elementsColor by remember {
        mutableStateOf(themeColors.onSurfaceVariant)
    }
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
                    if (!isTransitionActive && maxHeightDp == 0.dp) {
                        val newHeight = coordinates.size.height
                        val newHeightPx = with(density) { newHeight.toFloat() }
                        appBarHeight(newHeightPx)
                        maxHeightDp = with(density) { newHeight.toDp() }
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
                TopBarButtons(
                    iconTint = elementsColor,
                    onBackClick = {},
                    markBookClick = {},
                    shareClick = {}
                )
                Spacer(Modifier.padding(vertical = 4.dp))
                BoxWithConstraints(
                    contentAlignment = Alignment.TopStart,
                    modifier = Modifier
                        .clipToBounds()
                        .fillMaxWidth()
                        .height(logoSpaceAnimHolder.logoSpaceHeight.value)
                ) {
                    val maxWidth = this.maxWidth
                    logoSpaceWidth = maxWidth
                    EmployerLogo(
                        modifier = Modifier
                            .offset(x = logoSpaceAnimHolder.logoAnimPosition.value)
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
                            .size(logoSpaceAnimHolder.logoSizeAnim.value)
                            .clip(logoShape),
                        vacancyId = vacancyId,
                        employerLogo = employerLogo,
                        generatedBackgroundColors = { backgroundColor, elementColors ->
                            topBackgroundColor = backgroundColor
                            elementsColor = elementColors
                        }
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .width(if (logoSpaceWidth > 0.dp) logoSpaceWidth - 60.dp else Dp.Unspecified)
                            .offset(
                                x = logoSpaceAnimHolder.vacancyNameHorizontalPosition.value,
                                y = logoSpaceAnimHolder.vacancyNameVerticalPosition.value
                            )
                            .padding(12.dp)
                            .onGloballyPositioned { coordinates ->
                                vacancyNameHeight = with(density) {
                                    coordinates.size.height.toDp()
                                }
                            }
                            .sharedBounds(
                                rememberSharedContentState(DetailsTransition.vacancyName(vacancyId)),
                                animatedVisibilityScope = animatedVisibilityScope,
                                resizeMode = SharedTransitionScope.ResizeMode.RemeasureToBounds
                            ),
                        text = vacancyName,
                        textAlign = if (currentProgress < 0.5f) TextAlign.Center else TextAlign.Start,
                        maxLines = if (currentProgress > 0.5f) 2 else Int.MAX_VALUE,
                        overflow = TextOverflow.Ellipsis,
                        style = vacancyDetailsTypo().cardVacancyNameStyle.copy(fontSize = logoSpaceAnimHolder.vacancyNameSize.value.sp),
                    )
                }
                Spacer(Modifier.padding(vertical = 2.dp))
                if (employerName != null) {
                    Text(
                        text = employerName,
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
                }
                Spacer(Modifier.padding(vertical = 4.dp))
                if (address != null) {
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
                            text = address,
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
@Composable
fun Color.getBackGradient(): List<Color> = remember(this) {
    listOf(
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
}

@Stable
@Composable
fun Color.getLogoGradient(): List<Color> = remember(this) {
    listOf(
        this.copy(0.16f),
        this.copy(0.08f),
        this.copy(0.02f),
    )
}

@Stable
@Composable
fun topBarAnimHolder(
    maxHeightDp: Dp,
    collapseProgress: () -> Float,
): TopBarAnimHolder {
    val minHeight = TopAppBarDetailsDefaults.COLLAPSED
    val collapseProgress = collapseProgress()

    val appBarHeight = if (maxHeightDp != 0.dp) {
        lerp(maxHeightDp, minHeight, collapseProgress)
    } else Dp.Unspecified

    val animatedTopBarBox = animateDpAsState(appBarHeight, label = "height")
    val animatedRoundedCorner = animateDpAsState(
        lerp(48.dp, 0.dp, collapseProgress),
        label = "corner"
    )

    val textAlphaAnim = animateFloatAsState(
        lerp(1f, 0f, collapseProgress),
        label = "alpha"
    )

    return TopBarAnimHolder(
        animatedTopBarBox = animatedTopBarBox,
        animatedRoundedCorner = animatedRoundedCorner,
        textAlphaAnim = textAlphaAnim,
    )
}

@Stable
@Composable
fun logoSpaceAnim(
    width: Dp,
    vacancyNameHeightDp: Dp,
    collapseProgress: () -> Float,
): LogoSpaceAnimHolder {
    val collapseProgress = collapseProgress()
    val maxHeight: Dp = TopAppBarDetailsDefaults.LOGO_SPACE_MAX_HEIGHT + vacancyNameHeightDp
    val minHeight: Dp = TopAppBarDetailsDefaults.LOGO_SPACE_MIN_HEIGHT
    val logoDefSize: Dp = TopAppBarDetailsDefaults.LOGO_PIC_DEFAULT_SIZE
    val logoMinSize: Dp = TopAppBarDetailsDefaults.LOGO_PIC_MIN_SIZE
    val vacancyNameDefaultSize: Float = TopAppBarDetailsDefaults.VACANCY_NAME_DEFAULT_SIZE
    val vacancyNameMinSize: Float = TopAppBarDetailsDefaults.VACANCY_NAME_MIN_SIZE

    val logoAnimPosition = animateDpAsState(
        lerp(
            (width / 2) - (TopAppBarDetailsDefaults.LOGO_PIC_DEFAULT_SIZE / 2),
            12.dp,
            collapseProgress
        ),
        label = "logo_offset"
    )

    val logoSizeAnim = animateDpAsState(
        lerp(logoDefSize, logoMinSize, collapseProgress),
        label = "logo_size"
    )

    val logoSpaceHeight = animateDpAsState(
        lerp(maxHeight, minHeight, collapseProgress),
        label = "space_height"
    )

    val vacancyNameSize = animateFloatAsState(
        lerp(vacancyNameDefaultSize, vacancyNameMinSize, collapseProgress),
        label = "vacancy_name_size"
    )

    val vacancyNameVerticalPosition = animateDpAsState(
        if (vacancyNameHeightDp < TopAppBarDetailsDefaults.TWO_LINES_CONTAINER_HEIGHT) {
            lerp(0.dp, -(TopAppBarDetailsDefaults.LOGO_PIC_MIN_SIZE / 4), collapseProgress)
        } else 0.dp,
        label = "vacancy_name_vertical_position"
    )

    val vacancyNameHorizontalPosition = animateDpAsState(
        lerp(0.dp, width * 0.05f, collapseProgress),
        label = "vacancy_name_horizon_position"
    )

    return LogoSpaceAnimHolder(
        logoSpaceHeight = logoSpaceHeight,
        logoAnimPosition = logoAnimPosition,
        logoSizeAnim = logoSizeAnim,
        vacancyNameHorizontalPosition = vacancyNameHorizontalPosition,
        vacancyNameVerticalPosition = vacancyNameVerticalPosition,
        vacancyNameSize = vacancyNameSize
    )
}

@Immutable
data class TopBarAnimHolder(
    val animatedTopBarBox: State<Dp>,
    val animatedRoundedCorner: State<Dp>,
    val textAlphaAnim: State<Float>,
)

@Immutable
data class LogoSpaceAnimHolder(
    val logoSpaceHeight: State<Dp>,
    val logoAnimPosition: State<Dp>,
    val logoSizeAnim: State<Dp>,
    val vacancyNameHorizontalPosition: State<Dp>,
    val vacancyNameVerticalPosition: State<Dp>,
    val vacancyNameSize: State<Float>,
)

@Immutable
object TopAppBarDetailsDefaults {
    val COLLAPSED: Dp = 152.dp
    val LOGO_SPACE_MAX_HEIGHT: Dp = 132.dp
    val LOGO_SPACE_MIN_HEIGHT: Dp = 80.dp
    val LOGO_PIC_DEFAULT_SIZE: Dp = 100.dp
    val LOGO_PIC_MIN_SIZE: Dp = 48.dp
    val TWO_LINES_CONTAINER_HEIGHT: Dp = 48.dp
    const val VACANCY_NAME_DEFAULT_SIZE: Float = 18f
    const val VACANCY_NAME_MIN_SIZE: Float = 14f
}

