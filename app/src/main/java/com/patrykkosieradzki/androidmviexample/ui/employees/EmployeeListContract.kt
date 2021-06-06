package com.patrykkosieradzki.androidmviexample.ui.employees

import com.patrykkosieradzki.androidmviexample.utils.UiEffect
import com.patrykkosieradzki.androidmviexample.utils.UiEvent
import com.patrykkosieradzki.androidmviexample.utils.UiState

interface EmployeeListContract {

    sealed class Event : UiEvent

    data class State(
        override val inProgress: Boolean = false
    ) : UiState {
        override fun toSuccess() = copy(inProgress = false)
    }

    sealed class Effect : UiEffect
}
