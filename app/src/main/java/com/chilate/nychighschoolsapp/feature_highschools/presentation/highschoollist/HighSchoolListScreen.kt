package com.chilate.nychighschoolsapp.feature_highschools.presentation.highschoollist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.chilate.nychighschoolsapp.feature_highschools.data.remote.responses.HighSchool
import com.chilate.nychighschoolsapp.feature_highschools.presentation.util.navigation.Screen

/**
 * Created by Kevel on 3/2/2023.
 */

@Composable
fun HighSchoolListScreen(
    navController: NavHostController,
    viewModel: HighSchoolListViewModel
) {

    val viewState by remember { viewModel.modelStore.stateFlow }.collectAsState()

    HighSchoolListContent(
        navController = navController,
        viewState = viewState,
        viewModel = viewModel
    )
}

@Composable
fun HighSchoolListContent(
    navController: NavHostController,
    viewState: HighSchoolListState,
    viewModel: HighSchoolListViewModel
) {
    if (viewState.isCallFailed) {
        ErrorMessageComponent(
            errorMessage = viewState.errorMessage,
            viewModel = viewModel,
            modifier = Modifier
                .fillMaxSize()
        )
    } else {
        HighSchoolListComponent(
            navController = navController,
            viewState = viewState,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

@Composable
fun ErrorMessageComponent(
    modifier: Modifier = Modifier,
    errorMessage: String,
    viewModel: HighSchoolListViewModel
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = errorMessage,
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary
        )
        Button(onClick = {
            viewModel.onEvent(HighSchoolListEvent.TryAgain)
        }) {
            Text(
                text = " Try Again"
            )
        }
    }
}

@Composable
fun HighSchoolListComponent(
    navController: NavHostController,
    viewState: HighSchoolListState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(viewState.highSchoolList) { highSchool ->
            HighSchoolItem(
                item = highSchool,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                selectHighSchool = {
                    navController.navigate(Screen.Details.route + "/${highSchool.dbn}")
                }
            )
        }
    }
}

@Composable
fun HighSchoolItem(
    item: HighSchool,
    modifier: Modifier = Modifier,
    selectHighSchool: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Card(
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .clickable {
                    selectHighSchool()
                }
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 10.dp)
            ) {
                Text(
                    text = item.school_name,
                    fontSize = 22.sp,
                    color = MaterialTheme.colors.secondary
                )

                Text(
                    text = "${item.neighborhood}, ${item.zip}, ${item.city}",
                    fontSize = 20.sp,
                    color = MaterialTheme.colors.secondary
                )
            }
        }
    }
}