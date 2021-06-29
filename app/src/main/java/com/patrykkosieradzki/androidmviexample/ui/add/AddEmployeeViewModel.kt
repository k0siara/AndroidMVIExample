package com.patrykkosieradzki.androidmviexample.ui.add

import com.patrykkosieradzki.androidmviexample.domain.model.Address
import com.patrykkosieradzki.androidmviexample.domain.repositories.EmployeeRepository
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract.Event.*
import com.patrykkosieradzki.androidmviexample.utils.BaseComposeViewModel
import com.patrykkosieradzki.androidmviexample.utils.UiState
import com.patrykkosieradzki.androidmviexample.utils.asSuccess
import kotlinx.coroutines.delay

class AddEmployeeViewModel(
    private val employeeRepository: EmployeeRepository
) :
    BaseComposeViewModel<AddEmployeeContract.State, AddEmployeeContract.Event, AddEmployeeContract.Effect>(
        initialState = UiState.Loading
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
            gender = event.gender,
            address = event.address
        )
    }

    private fun handleAddAddressEvent(event: AddAddressEvent) {
        updateForm(
            address = "",
            addresses = currentState.asSuccess.addresses.plus(Address(currentState.asSuccess.address))
        )
    }

    private fun handleRemoveAddressEvent(event: RemoveAddressEvent) {
        val addresses = currentState.asSuccess.addresses.toMutableList()
        addresses.remove(event.address)
        updateForm(addresses = addresses)
    }

    private fun handleSaveEmployeeEvent(event: SaveEmployeeEvent) {

    }

    private fun loadGenders() {
        safeLaunch {
            delay(3000)
            val genders = employeeRepository.getGenders()
            updateUiState { UiState.Success(AddEmployeeContract.State(genders = genders)) }
        }
    }

    private fun updateForm(
        firstName: String? = null,
        lastName: String? = null,
        gender: String? = null,
        address: String? = null,
        addresses: List<Address>? = null,
    ) {
        updateUiState {
            UiState.Success(
                AddEmployeeContract.State(
                    firstName = firstName ?: it.asSuccess.firstName,
                    lastName = lastName ?: it.asSuccess.lastName,
                    gender = gender ?: it.asSuccess.gender,
                    address = address ?: it.asSuccess.address,
                    addresses = addresses ?: it.asSuccess.addresses
                )
            )
        }

    }
}
