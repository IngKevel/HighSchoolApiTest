package com.chilate.nychighschoolsapp.feature_highschools.presentation.selectedhighschool

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
class SelectedHighSchoolViewModel @Inject constructor(
    private val repository: HighSchoolRepository
): ViewModel() {

    val modelStore = FlowModelStore(SelectedHighSchoolState())

    fun onEvent(event: SelectedHighSchoolEvent) {
        when (event) {
            is SelectedHighSchoolEvent.GetSelectedHighSchool -> getSelectedHighSchool(event.dbn)
        }
    }

    private fun getSelectedHighSchool(dbn: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.getSelectedHighSchool(dbn)

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
                        if (result.data.isNotEmpty()) {
                            modelStore.process { oldState ->
                                oldState.copy(
                                    selectedHighSchool = result.data.first(),
                                    isEmptyList = false,
                                    isCallFailed = false
                                )
                            }
                        } else {
                            modelStore.process { oldState ->
                                oldState.copy(
                                    isCallFailed = false,
                                    isEmptyList = true
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}