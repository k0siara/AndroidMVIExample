package com.patrykkosieradzki.androidmviexample.ui.features.add

import com.patrykkosieradzki.androidmviexample.domain.model.Address
import com.patrykkosieradzki.androidmviexample.domain.model.Gender
import com.patrykkosieradzki.androidmviexample.utils.UiEffect
import com.patrykkosieradzki.androidmviexample.utils.UiEvent

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

    data class State(
        val firstName: String = "",
        val lastName: String = "",
        val age: Int = 0,
        val gender: String = "",
        val address: String = "",
        val addresses: List<Address> = emptyList(),
        val genders: List<Gender> = emptyList(),
    )

    sealed class Effect : UiEffect
}
