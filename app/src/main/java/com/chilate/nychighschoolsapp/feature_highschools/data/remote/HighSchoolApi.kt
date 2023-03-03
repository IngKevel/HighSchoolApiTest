package com.chilate.nychighschoolsapp.feature_highschools.data.remote

import com.chilate.nychighschoolsapp.feature_highschools.data.remote.responses.HighSchool
import com.chilate.nychighschoolsapp.feature_highschools.data.remote.responses.SelectedHighSchool
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by Kevel on 3/2/2023.
 */
interface HighSchoolApi {

    @GET("s3k6-pzi2.json")
    suspend fun getHighSchoolList(): List<HighSchool>

    @GET("f9bf-2cp4.json")
    suspend fun getSelectedHighSchool(
        @Query("dbn") dbn: String
    ): List<SelectedHighSchool>
}