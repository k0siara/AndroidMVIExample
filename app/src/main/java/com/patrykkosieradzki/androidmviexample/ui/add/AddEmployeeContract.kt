package com.patrykkosieradzki.androidmviexample.ui.add

import com.patrykkosieradzki.androidmviexample.domain.model.Address
import com.patrykkosieradzki.androidmviexample.domain.model.Gender
import com.patrykkosieradzki.androidmviexample.utils.UiEffect
import com.patrykkosieradzki.androidmviexample.utils.UiEvent
import com.patrykkosieradzki.androidmviexample.utils.UiState

class AddEmployeeContract {
    sealed class Event : UiEvent

    abstract class FormState(
        val firstName: String = "",
        val lastName: String = "",
        val addresses: List<Address> = mutableListOf()
    ) : UiState {
        override val isLoading: Boolean = false
    }

    sealed class State : FormState() {
        class Initial(val genders: List<Gender>) : State() {
            override val isLoading: Boolean = false
        }

        class Loading(override val isLoading: Boolean = true) : State()
    }

    sealed class Effect : UiEffect
}
