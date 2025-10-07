package com.example.recruiterhunter.ui.components.placeholder_icon

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.recruiterhunter.R

@Composable
fun JobIcon() {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.baseline_work_24),
        contentDescription = "Заглушка вакансии",
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp),
    )
}