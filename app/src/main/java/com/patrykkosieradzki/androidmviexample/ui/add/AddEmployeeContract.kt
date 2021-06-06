package com.patrykkosieradzki.androidmviexample.ui.add

import com.patrykkosieradzki.androidmviexample.utils.UiEffect
import com.patrykkosieradzki.androidmviexample.utils.UiEvent
import com.patrykkosieradzki.androidmviexample.utils.UiState

class AddEmployeeContract {
    sealed class Event : UiEvent

    sealed class State : UiState {
        class Loading(override val isLoading: Boolean = true) : State()
    }

    sealed class Effect : UiEffect
}
