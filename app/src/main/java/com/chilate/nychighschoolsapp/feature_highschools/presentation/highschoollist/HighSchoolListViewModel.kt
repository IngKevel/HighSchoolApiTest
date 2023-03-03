package com.chilate.nychighschoolsapp.feature_highschools.presentation.highschoollist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chilate.nychighschoolsapp.feature_highschools.data.util.Resource
import com.chilate.nychighschoolsapp.feature_highschools.domain.repository.HighSchoolRepository
import com.chilate.nychighschoolsapp.feature_highschools.presentation.util.FlowModelStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Kevel on 3/2/2023.
 */

@Singleton
class HighSchoolListViewModel @Inject constructor(
    private val repository: HighSchoolRepository
): ViewModel() {

    val modelStore = FlowModelStore(HighSchoolListState())

    fun onEvent(event: HighSchoolListEvent) {
        when(event) {
            is HighSchoolListEvent.TryAgain -> getHighSchoolList()
        }
    }

    init {
        getHighSchoolList()
    }

    private fun getHighSchoolList() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getHighSchoolList()

            when(result) {
                is Resource.Error -> {
                    modelStore.process { oldState ->
                        oldState.copy(
                            isCallFailed = true,
                            errorMessage = result.message ?: ""
                        )
                    }
                }
                is Resource.Success -> {
                    if (result.data != null) {
                        modelStore.process { oldState ->
                            oldState.copy(
                                isCallFailed = false,
                                highSchoolList = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}