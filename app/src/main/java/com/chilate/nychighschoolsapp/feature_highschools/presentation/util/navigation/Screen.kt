package com.chilate.nychighschoolsapp.feature_highschools.presentation.util.navigation

/**
 * Created by Kevel on 3/2/2023.
 */
sealed class Screen(val route: String) {
    object List: Screen("list_screen")
    object Details: Screen("details_screen")
}
