package com.patrykkosieradzki.androidmviexample.ui.add

import com.patrykkosieradzki.androidmviexample.domain.model.Address
import com.patrykkosieradzki.androidmviexample.domain.repositories.EmployeeRepository
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract.Event.*
import com.patrykkosieradzki.androidmviexample.utils.BaseComposeViewModel
import kotlinx.coroutines.delay

class AddEmployeeComposeViewModel(
    private val employeeRepository: EmployeeRepository
) :
    BaseComposeViewModel<AddEmployeeContract.State, AddEmployeeContract.Event, AddEmployeeContract.Effect>(
        initialState = AddEmployeeContract.State.Loading()
    ) {

    init {
        loadGenders()
    }

    override fun handleEvent(event: AddEmployeeContract.Event) {
        when (event) {
            is UpdateFormEvent -> handleUpdateFormEvent(event)
            is AddAddressEvent -> handleAddAddressEvent(event)
            is RemoveAddressEvent -> handleRemoveAddressEvent(event)
            is SaveEmployeeEvent -> handleSaveEmployeeEvent(event)
        }
    }

    private fun handleUpdateFormEvent(event: UpdateFormEvent) {
        updateForm(
            firstName = event.firstName,
            lastName = event.lastName,
            address = event.address
        )
    }

    private fun handleAddAddressEvent(event: AddAddressEvent) {
        updateForm(
            address = "",
            addresses = currentState.addresses.plus(Address(currentState.address))
        )
    }

    private fun handleRemoveAddressEvent(event: RemoveAddressEvent) {
        val addresses = currentState.addresses.toMutableList()
        addresses.remove(event.address)
        updateForm(addresses = addresses)
    }

    private fun handleSaveEmployeeEvent(event: SaveEmployeeEvent) {

    }

    private fun loadGenders() {
        safeLaunch {
            delay(3000)
            val genders = employeeRepository.getGenders()
            updateUiState { AddEmployeeContract.State.Initial(genders) }
        }
    }

    private fun updateForm(
        firstName: String? = null,
        lastName: String? = null,
        address: String? = null,
        addresses: List<Address>? = null,
    ) {
        updateUiState {
            AddEmployeeContract.State.FormUpdated(
                firstName = firstName ?: currentState.firstName,
                lastName = lastName ?: currentState.lastName,
                address = address ?: currentState.address,
                addresses = addresses ?: currentState.addresses
            )
        }
    }
}
