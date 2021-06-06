package com.patrykkosieradzki.androidmviexample.ui.employees

import com.patrykkosieradzki.androidmviexample.utils.UiEffect
import com.patrykkosieradzki.androidmviexample.utils.UiEvent
import com.patrykkosieradzki.androidmviexample.utils.UiState

interface EmployeeListContract {

    sealed class Event : UiEvent

    sealed class State : UiState {
        class Loading(override val isLoading: Boolean = true) : State()
        class Success(override val isLoading: Boolean = false) : State()
        class Empty(override val isLoading: Boolean = false) : State()
        class Error(override val isLoading: Boolean = false) : State()
    }

    sealed class Effect : UiEffect
}
