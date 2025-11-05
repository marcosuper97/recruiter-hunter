package com.example.recruiterhunter.ui.components.details_top_bar


import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.recruiterhunter.R

@Composable
fun TopBarButtons(
    iconTint: Color,
    onBackClick: () -> Unit,
    markBookClick: () -> Unit,
    shareClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp)
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.baseline_arrow_back_24),
            tint = iconTint,
            contentDescription = "",
            modifier = Modifier
                .size(28.dp)
                .clickable(
                    enabled = true,
                    onClick = {
                        onBackClick()
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                )
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.baseline_share_24),
            tint = iconTint,
            contentDescription = "",
            modifier = Modifier
                .size(28.dp)
                .clickable(
                    enabled = true,
                    onClick = {
                        shareClick()
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                )
        )

        Spacer(Modifier.padding(horizontal = 12.dp))

        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.outline_bookmark_add_24),
            tint = iconTint,
            contentDescription = "",
            modifier = Modifier
                .size(28.dp)
                .clickable(
                    enabled = true,
                    onClick = {
                        markBookClick()
                    },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                )
        )
    }
}