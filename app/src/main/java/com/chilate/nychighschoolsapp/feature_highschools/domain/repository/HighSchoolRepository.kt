package com.chilate.nychighschoolsapp.feature_highschools.domain.repository

import com.chilate.nychighschoolsapp.feature_highschools.data.remote.responses.HighSchool
import com.chilate.nychighschoolsapp.feature_highschools.data.remote.responses.SelectedHighSchool
import com.chilate.nychighschoolsapp.feature_highschools.data.util.Resource

/**
 * Created by Kevel on 3/2/2023.
 */
interface HighSchoolRepository {

    suspend fun getHighSchoolList(): Resource<List<HighSchool>>
    suspend fun getSelectedHighSchool(dbn: String): Resource<List<SelectedHighSchool>>
}