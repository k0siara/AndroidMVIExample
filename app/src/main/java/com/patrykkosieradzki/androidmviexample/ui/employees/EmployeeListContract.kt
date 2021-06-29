package com.patrykkosieradzki.androidmviexample.ui.employees

import com.patrykkosieradzki.androidmviexample.utils.UiEffect
import com.patrykkosieradzki.androidmviexample.utils.UiEvent
import com.patrykkosieradzki.androidmviexample.utils.UiState

interface EmployeeListContract {

    sealed class Event : UiEvent

    data class State(
        val name: String = ""
    )

    sealed class Effect : UiEffect
}
