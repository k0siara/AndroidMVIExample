package com.patrykkosieradzki.androidmviexample.ui.add

import com.patrykkosieradzki.androidmviexample.domain.model.Address
import com.patrykkosieradzki.androidmviexample.domain.repositories.EmployeeRepository
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract.Event.*
import com.patrykkosieradzki.androidmviexample.utils.BaseComposeViewModel
import com.patrykkosieradzki.androidmviexample.utils.UiState
import com.patrykkosieradzki.androidmviexample.utils.successData
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
            is SaveEmployeeEvent -> handleSaveEmployeeEvent()
        }
    }

    private fun handleUpdateFormEvent(event: UpdateFormEvent) {
        updateUiSuccessState {
            it.copy(
                firstName = event.firstName ?: it.firstName,
                lastName = event.lastName ?: it.lastName,
                gender = event.gender ?: it.gender,
                address = event.address ?: it.address
            )
        }
    }

    private fun handleAddAddressEvent(event: AddAddressEvent) {
        updateUiSuccessState {
            it.copy(
                address = "",
                addresses = currentState.successData.addresses.plus(Address(currentState.successData.address))
            )
        }
    }

    private fun handleRemoveAddressEvent(event: RemoveAddressEvent) {
        val addresses = currentState.successData.addresses.toMutableList()
        addresses.remove(event.address)
        updateUiSuccessState {
            it.copy(
                addresses = addresses
            )
        }
    }

    private fun handleSaveEmployeeEvent() {
        currentState.successData.run {
            val isValid =
                firstName.isNotEmpty() && lastName.isNotEmpty() && gender.isNotEmpty() && addresses.isNotEmpty()
            if (!isValid) {
                showSnackbar("Form is not valid")
            }
        }
    }

    private fun loadGenders() {
        safeLaunch {
            delay(1000)
            val genders = employeeRepository.getGenders()
            updateUiState { UiState.Success(AddEmployeeContract.State(genders = genders)) }
        }
    }
}
