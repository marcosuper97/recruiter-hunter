package com.example.recruiterhunter.ui.components.key_skills

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.recruiterhunter.R
import com.example.recruiterhunter.domain.model.theme_state.ActualTheme
import com.example.recruiterhunter.ui.theme.RecruiterHunterTheme
import com.example.recruiterhunter.ui.theme.vacancyDetailsTypo

@Composable
fun KeySkillsBar(keySkills: List<String>) {
    val themeColors = MaterialTheme.colorScheme
    val state = rememberLazyListState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = stringResource(R.string.key_skills),
            style = vacancyDetailsTypo().detailsTitleText
        )
        Spacer(Modifier.padding(vertical = 4.dp))
        LazyRow(state = state, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
            items(keySkills) { keySkill ->
                Box(
                    modifier = Modifier
                        .padding(horizontal = 2.dp)
                        .background(
                            color = themeColors.surfaceContainer,
                            shape = RoundedCornerShape(6.dp)
                        )
                ) {
                    Text(
                        text = keySkill,
                        modifier = Modifier.padding(8.dp),
                        style = vacancyDetailsTypo().vacancyKeySkills
                    )
                }
            }
        }
    }
}

val keySkills = listOf(
    "System Design",
    "Cloud computing (AWS/GCP/Azure)",
    "Docker & контейнеризация",
    "Git & контроль версий",
    "API development (REST/GraphQL)",
    "SQL/NoSQL databases",
    "DevOps practices",
    "Teamwork & коммуникация",
    "Аналитическое мышление",
    "Постоянное обучение"
)

@Preview(showBackground = true)
@Composable
fun PreviewKeySkills() {
    RecruiterHunterTheme(ActualTheme.DARK) {
        Scaffold(Modifier.fillMaxSize()) { innerPadding ->
            KeySkillsBar(keySkills)
        }
    }
}

