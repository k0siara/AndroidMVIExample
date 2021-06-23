package com.patrykkosieradzki.androidmviexample.ui.add

import com.patrykkosieradzki.androidmviexample.domain.model.Address
import com.patrykkosieradzki.androidmviexample.domain.model.Gender
import com.patrykkosieradzki.androidmviexample.utils.UiEffect
import com.patrykkosieradzki.androidmviexample.utils.UiEvent
import com.patrykkosieradzki.androidmviexample.utils.UiState

class AddEmployeeContract {
    sealed class Event : UiEvent {
        object AddAddressEvent : Event()
        class RemoveAddressEvent(val address: Address) : Event()
        class UpdateFormEvent(
            val firstName: String? = null,
            val lastName: String? = null,
            val gender: String? = null,
            val address: String? = null,
        ) : Event()

        object SaveEmployeeEvent : Event()
    }

    abstract class FormState(
        open val firstName: String,
        open val lastName: String,
        open val gender: String,
        open val address: String,
        open val addresses: List<Address>,
        open val genders: List<Gender>
    ) : UiState {
        override val isLoading: Boolean = false
    }

    sealed class State(
        override val firstName: String = "",
        override val lastName: String = "",
        override val gender: String = "",
        override val address: String = "",
        override val addresses: List<Address> = mutableListOf(),
        override val genders: List<Gender> = mutableListOf()
    ) : FormState(firstName, lastName, gender, address, addresses, genders) {
        class Initial(override val genders: List<Gender>) : State() {
            override val isLoading: Boolean = false
        }

        data class FormUpdated(
            override val firstName: String,
            override val lastName: String,
            override val gender: String,
            override val address: String,
            override val addresses: List<Address>
        ) : State() {
            override val isLoading: Boolean = false
        }

        class Loading(override val isLoading: Boolean = true) : State()
    }

    sealed class Effect : UiEffect
}
