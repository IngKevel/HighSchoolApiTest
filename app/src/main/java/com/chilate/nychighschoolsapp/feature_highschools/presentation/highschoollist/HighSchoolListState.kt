package com.chilate.nychighschoolsapp.feature_highschools.presentation.highschoollist

import com.chilate.nychighschoolsapp.feature_highschools.data.remote.responses.HighSchool

/**
 * Created by Kevel on 3/2/2023.
 */
data class HighSchoolListState(
    val highSchoolList: List<HighSchool> = emptyList(),
    val isCallFailed: Boolean = false,
    val errorMessage: String = ""
)
