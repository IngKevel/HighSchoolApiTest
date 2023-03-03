package com.chilate.nychighschoolsapp.feature_highschools.presentation.selectedhighschool

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Kevel on 3/2/2023.
 */

@Composable
fun SelectedHighSchoolScreen(
    dbn: String,
    viewModel: SelectedHighSchoolViewModel
) {
    val viewState by remember { viewModel.modelStore.stateFlow }.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.onEvent(SelectedHighSchoolEvent.GetSelectedHighSchool(dbn))
    }

    if (viewState.isCallFailed) {
        ErrorMessageComponent(
            errorMessage = viewState.errorMessage,
            viewModel = viewModel,
            dbn = dbn,
            modifier = Modifier
                .fillMaxSize()
        )
    }
    else if (viewState.isEmptyList) {
        EmptyHighSchoolsDetails()
    } else {
        SelectedHighSchoolContent(
            viewState = viewState
        )
    }
}

@Composable
fun ErrorMessageComponent(
    modifier: Modifier = Modifier,
    errorMessage: String,
    viewModel: SelectedHighSchoolViewModel,
    dbn: String
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
            viewModel.onEvent(SelectedHighSchoolEvent.GetSelectedHighSchool(dbn))
        }) {
            Text(
                text = " Try Again"
            )
        }
    }
}

@Composable
fun EmptyHighSchoolsDetails() {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "No High Schools Details",
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary
        )
    }
}

@Composable
fun SelectedHighSchoolContent(
    viewState: SelectedHighSchoolState
) {
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = viewState.selectedHighSchool.school_name,
            fontSize = 22.sp,
            color = MaterialTheme.colors.primary
        )

        Card(
            backgroundColor = MaterialTheme.colors.primary,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(10.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "SAT Score:",
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.secondary
                    )
                    Text(
                        text = viewState.selectedHighSchool.num_of_sat_test_takers,
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.secondary
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Math Score:",
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.secondary
                    )
                    Text(
                        text = viewState.selectedHighSchool.sat_math_avg_score,
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.secondary
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Writing Score:",
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.secondary
                    )
                    Text(
                        text = viewState.selectedHighSchool.sat_writing_avg_score,
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.secondary
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Reading Score:",
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.secondary
                    )
                    Text(
                        text = viewState.selectedHighSchool.sat_critical_reading_avg_score,
                        fontSize = 20.sp,
                        color = MaterialTheme.colors.secondary
                    )
                }
            }
        }
    }
}