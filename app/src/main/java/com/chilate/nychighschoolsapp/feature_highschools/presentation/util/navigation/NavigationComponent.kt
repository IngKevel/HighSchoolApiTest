package com.chilate.nychighschoolsapp.feature_highschools.presentation.util.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.chilate.nychighschoolsapp.feature_highschools.presentation.highschoollist.HighSchoolListScreen
import com.chilate.nychighschoolsapp.feature_highschools.presentation.highschoollist.HighSchoolListViewModel
import com.chilate.nychighschoolsapp.feature_highschools.presentation.selectedhighschool.SelectedHighSchoolScreen
import com.chilate.nychighschoolsapp.feature_highschools.presentation.selectedhighschool.SelectedHighSchoolViewModel

/**
 * Created by Kevel on 3/2/2023.
 */

@Composable
fun NavigationComponent(
    navController: NavHostController,
    highSchoolListViewModel: HighSchoolListViewModel,
    selectedHighSchoolViewModel: SelectedHighSchoolViewModel
) {
    NavHost(navController = navController, startDestination = Screen.List.route) {
        composable(route = Screen.List.route) {
            HighSchoolListScreen(
                navController = navController,
                viewModel = highSchoolListViewModel
            )
        }
        composable(
            route = Screen.Details.route + "/{dbn}",
            arguments = listOf(
                navArgument(name = "dbn") {
                    type = NavType.StringType
                }
            )
        ) {
            val dbn = remember {
                it.arguments?.getString("dbn")
            }

            SelectedHighSchoolScreen(
                dbn = dbn ?: "",
                viewModel = selectedHighSchoolViewModel
            )
        }
    }
}