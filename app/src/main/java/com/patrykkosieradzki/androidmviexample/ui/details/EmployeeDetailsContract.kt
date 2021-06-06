package com.patrykkosieradzki.androidmviexample.ui.details

import com.patrykkosieradzki.androidmviexample.utils.UiEffect
import com.patrykkosieradzki.androidmviexample.utils.UiEvent
import com.patrykkosieradzki.androidmviexample.utils.UiState

interface EmployeeDetailsContract {
    sealed class Event : UiEvent

    sealed class State : UiState {
        class Loading(override val isLoading: Boolean = true) : State()
    }

    sealed class Effect : UiEffect
}
