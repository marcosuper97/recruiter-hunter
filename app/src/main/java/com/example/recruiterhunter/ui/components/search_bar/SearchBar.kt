package com.example.recruiterhunter.ui.components.search_bar

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSearchBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recruiterhunter.R
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.ui.theme.RecruiterHunterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarDock(
    state: TextFieldState,
    placeholder: String = "Поиск",
    onSearch: ((String) -> Unit)? = null,
    filterIcon: ImageVector? = null,
    labelIcon: ImageVector? = null,
    onFilterClick: (() -> Unit)? = null,
    filterState: MutableState<Boolean> = mutableStateOf(false)
) {
    val filterState by filterState
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val roundedCorner = remember { RoundedCornerShape(24.dp) }
    val colors = SearchBarDefaults.colors()
    val searchBarColors = remember { colors.inputFieldColors }
    val textStyle =
        MaterialTheme.typography.bodyLarge.copy(color = searchBarColors.focusedTextColor)
    val filterBackgroundColor by animateColorAsState(
        targetValue = if (filterState)
            searchBarColors.focusedPlaceholderColor.copy(0.7f)
        else
            searchBarColors.focusedPlaceholderColor.copy(0.4f),
        animationSpec = tween()
    )
    val searchBackgroundElevation by animateDpAsState(
        if (isFocused) 0.dp else 6.dp
    )

    BasicTextField(
        cursorBrush = SolidColor(searchBarColors.cursorColor),
        state = state,
        textStyle = textStyle,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
            keyboardType = KeyboardType.Text
        ),
        onKeyboardAction = {
            onSearch?.let {
                if (state.text.isNotEmpty()) onSearch(state.text.toString())
            }
        },
        lineLimits = TextFieldLineLimits.SingleLine,
        interactionSource = interactionSource,
        modifier = Modifier
            .padding(12.dp),
        decorator = { innerTextField ->
            Row(
                Modifier
                    .shadow(searchBackgroundElevation, roundedCorner)
                    .background(
                        searchBarColors.focusedContainerColor,
                        roundedCorner
                    )
                    .fillMaxWidth()
                    .height(56.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.padding(start = 16.dp))
                labelIcon?.let {
                    Icon(
                        it,
                        contentDescription = "Mail Icon",
                        tint = searchBarColors.unfocusedLeadingIconColor,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
                if (!isFocused && state.text.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            color = searchBarColors.unfocusedPlaceholderColor
                        )
                    )
                }
                innerTextField()
                filterIcon?.let {
                    Spacer(modifier = Modifier.weight(1f))
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .size(30.dp)
                            .background(filterBackgroundColor, CircleShape)
                            .clickable(onClick = { onFilterClick?.invoke() }),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(
                            imageVector = it,
                            contentDescription = "Иконка фильтра",
                            tint = searchBarColors.unfocusedLeadingIconColor
                        )
                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun PreviewSearchBars() {
    RecruiterHunterTheme(ActualTheme.LIGHT) {
        val state = rememberTextFieldState()
        Scaffold {
            Column {
                SearchBarDock(
                    state = state,
                    onSearch = { query ->
                        Log.d("zapros", query)
                    },
                    filterIcon = ImageVector.vectorResource(R.drawable.baseline_filter_list_24),
                    labelIcon = ImageVector.vectorResource(R.drawable.baseline_search_24),
                    onFilterClick = {}
                )

                SearchBar(
                    rememberSearchBarState(),
                    {}
                )

            }

        }
    }
}
