package com.chilate.nychighschoolsapp.feature_highschools.presentation.selectedhighschool

import com.chilate.nychighschoolsapp.feature_highschools.data.remote.responses.SelectedHighSchool

/**
 * Created by Kevel on 3/2/2023.
 */
data class SelectedHighSchoolState(
    val selectedHighSchool: SelectedHighSchool = SelectedHighSchool(),
    val isEmptyList: Boolean = false,
    val isCallFailed: Boolean = false,
    val errorMessage: String = ""
)
