package com.patrykkosieradzki.androidmviexample.ui.details

import com.patrykkosieradzki.androidmviexample.utils.UiEffect
import com.patrykkosieradzki.androidmviexample.utils.UiEvent

interface EmployeeDetailsContract {
    sealed class Event : UiEvent

    data class State(
        val name: String = ""
    )

    sealed class Effect : UiEffect
}
