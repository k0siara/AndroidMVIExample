package com.patrykkosieradzki.androidmviexample.ui.features.employees

import com.patrykkosieradzki.androidmviexample.utils.UiEffect
import com.patrykkosieradzki.androidmviexample.utils.UiEvent

interface EmployeeListContract {

    sealed class Event : UiEvent

    data class State(
        val name: String = ""
    )

    sealed class Effect : UiEffect
}
