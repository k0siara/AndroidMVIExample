package com.patrykkosieradzki.androidmviexample.ui.add

import com.patrykkosieradzki.androidmviexample.domain.model.Address
import com.patrykkosieradzki.androidmviexample.domain.repositories.EmployeeRepository
import com.patrykkosieradzki.androidmviexample.utils.BaseViewModel

class AddEmployeeViewModel(
    private val employeeRepository: EmployeeRepository
) :
    BaseViewModel<AddEmployeeContract.State, AddEmployeeContract.Event, AddEmployeeContract.Effect>(
        initialState = AddEmployeeContract.State.Loading()
    ) {

    override fun initialize() {
        super.initialize()
        loadGenders()
    }

    override fun handleEvent(event: AddEmployeeContract.Event) {
        when (event) {
            is AddEmployeeContract.Event.AddAddressEvent -> {
                updateForm(
                    address = "",
                    addresses = currentState.addresses.plus(Address(currentState.address))
                )
            }
            is AddEmployeeContract.Event.RemoveAddressEvent -> {
                val addresses = currentState.addresses.toMutableList()
                addresses.remove(event.address)
                updateForm(addresses = addresses)
            }
        }
    }

    private fun loadGenders() {
        safeLaunch {
            val genders = employeeRepository.getGenders()
            updateUiState { AddEmployeeContract.State.Initial(genders) }
        }
    }

    fun updateForm(
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

    fun onAddAddressClicked() {
        setUiEvent(AddEmployeeContract.Event.AddAddressEvent)
    }

    fun onRemoveAddressClicked(address: Address) {
        setUiEvent(AddEmployeeContract.Event.RemoveAddressEvent(address))
    }
}
