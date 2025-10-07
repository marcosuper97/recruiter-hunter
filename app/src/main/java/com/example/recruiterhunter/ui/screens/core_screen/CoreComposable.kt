package com.example.recruiterhunter.ui.screens.core_screen

import android.net.Uri
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.recruiterhunter.presentation.app_theme_vm.AppThemeViewModel
import com.example.recruiterhunter.ui.screens.details_screen.VacancyDetails
import com.example.recruiterhunter.ui.screens.search_screen.SearchScreen
import com.example.recruiterhunter.ui.screens.setting_screen.SettingsScreen
import com.example.recruiterhunter.ui.theme.RecruiterHunterTheme
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun CoreCompose(
    viewModel: AppThemeViewModel = koinViewModel()
) {
    val actualTheme by viewModel.actualThemeState.collectAsStateWithLifecycle()
    val navController = rememberNavController()
    RecruiterHunterTheme(actualTheme) {
        SharedTransitionLayout(modifier = Modifier.fillMaxSize()) {
            NavHost(navController, startDestination = "search_screen") {
                composable("search_screen") {
                    SearchScreen(
                        navController = navController,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@composable
                    )
                }
                composable(
                    route = "job_detail/{vacancyId}/{vacancyName}/{employerName}/{employerLogo}/{address}/{salary}",
                    arguments = listOf(
                        navArgument("vacancyId") { type = NavType.LongType },
                        navArgument("vacancyName") { type = NavType.StringType },
                        navArgument("employerName") { type = NavType.StringType },
                        navArgument("employerLogo") { type = NavType.StringType },
                        navArgument("address") { type = NavType.StringType },
                        navArgument("salary") { type = NavType.StringType },
                    )
                ) { backStackEntry ->
                    val vacancyId = backStackEntry.arguments?.getLong("vacancyId")
                    val vacancyName = backStackEntry.arguments?.getString("vacancyName")?.let { Uri.decode(it) }
                    val employerName = backStackEntry.arguments?.getString("employerName")?.let { Uri.decode(it) }
                    val employerLogo = backStackEntry.arguments?.getString("employerLogo")?.let { Uri.decode(it) }
                    val address = backStackEntry.arguments?.getString("address")?.let { Uri.decode(it) }
                    val salary = backStackEntry.arguments?.getString("salary")?.let { Uri.decode(it) }
                    VacancyDetails(
                        vacancyId = vacancyId,
                        employerLogo = employerLogo,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@composable
                    )
                }
                composable("settings_screen") {
                    SettingsScreen(
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedVisibilityScope = this@composable,
                        actualTheme = actualTheme
                    )
                }
            }
        }
    }
}
