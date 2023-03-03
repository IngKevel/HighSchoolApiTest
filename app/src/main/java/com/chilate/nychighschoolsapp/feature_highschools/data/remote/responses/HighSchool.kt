package com.chilate.nychighschoolsapp.feature_highschools.data.remote.responses

/**
 * Created by Kevel on 3/2/2023.
 */

data class HighSchool(
    val dbn: String,
    val school_name: String,
    val neighborhood: String,
    val city: String,
    val zip: String
)
