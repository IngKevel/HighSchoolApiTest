package com.chilate.nychighschoolsapp.feature_highschools.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.chilate.nychighschoolsapp.feature_highschools.presentation.highschoollist.HighSchoolListScreen
import com.chilate.nychighschoolsapp.feature_highschools.presentation.highschoollist.HighSchoolListViewModel
import com.chilate.nychighschoolsapp.feature_highschools.presentation.selectedhighschool.SelectedHighSchoolViewModel
import com.chilate.nychighschoolsapp.feature_highschools.presentation.util.navigation.NavigationComponent
import com.chilate.nychighschoolsapp.ui.theme.NYCHighSchoolsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var highSchoolListViewModel: HighSchoolListViewModel

    @Inject
    lateinit var selectedHighSchoolViewModel: SelectedHighSchoolViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NYCHighSchoolsAppTheme {
                val navController = rememberNavController()
                Surface(
                    color = MaterialTheme.colors.background,
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    NavigationComponent(
                        navController = navController,
                        highSchoolListViewModel = highSchoolListViewModel,
                        selectedHighSchoolViewModel = selectedHighSchoolViewModel
                    )
                }
            }
        }
    }
}
