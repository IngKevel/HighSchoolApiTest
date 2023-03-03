package com.chilate.nychighschoolsapp.feature_highschools.data.repository

import com.chilate.nychighschoolsapp.feature_highschools.data.remote.HighSchoolApi
import com.chilate.nychighschoolsapp.feature_highschools.data.remote.responses.HighSchool
import com.chilate.nychighschoolsapp.feature_highschools.data.remote.responses.SelectedHighSchool
import com.chilate.nychighschoolsapp.feature_highschools.data.util.Resource
import com.chilate.nychighschoolsapp.feature_highschools.domain.repository.HighSchoolRepository
import javax.inject.Inject

/**
 * Created by Kevel on 3/2/2023.
 */
class HighSchoolRepositoryImpl @Inject constructor(
    private val api: HighSchoolApi
): HighSchoolRepository {

    override suspend fun getHighSchoolList(): Resource<List<HighSchool>> {
        val response = try {
            api.getHighSchoolList()
        } catch (e:Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }

    override suspend fun getSelectedHighSchool(dbn: String): Resource<List<SelectedHighSchool>> {
        val response = try {
            api.getSelectedHighSchool(dbn)
        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }
        return Resource.Success(response)
    }
}