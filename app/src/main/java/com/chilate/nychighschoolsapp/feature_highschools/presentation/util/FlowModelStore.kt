package com.chilate.nychighschoolsapp.feature_highschools.presentation.util

import kotlinx.coroutines.flow.*

/**
 * Created by Kevel on 3/2/2023.
 */
class FlowModelStore<ViewState>(initialState: ViewState) {

    private val _stateFlow: MutableStateFlow<ViewState> = MutableStateFlow(initialState)
    val stateFlow: StateFlow<ViewState> get() = _stateFlow.asStateFlow()

    fun process(stateChange: (ViewState) -> ViewState) {
        stateChange.invoke(_stateFlow.value).run {
            _stateFlow.update(stateChange)
        }
    }
}