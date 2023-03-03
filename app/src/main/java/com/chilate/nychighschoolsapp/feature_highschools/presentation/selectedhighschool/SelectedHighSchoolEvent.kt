package com.chilate.nychighschoolsapp.feature_highschools.presentation.selectedhighschool

/**
 * Created by Kevel on 3/2/2023.
 */
sealed class SelectedHighSchoolEvent {
    data class GetSelectedHighSchool(val dbn: String): SelectedHighSchoolEvent()
}
