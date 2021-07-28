package com.patrykkosieradzki.androidmviexample.ui.add

import com.patrykkosieradzki.androidmviexample.domain.model.Address
import com.patrykkosieradzki.androidmviexample.domain.model.Employee
import com.patrykkosieradzki.androidmviexample.domain.usecases.GetGendersUseCase
import com.patrykkosieradzki.androidmviexample.domain.usecases.SaveEmployeeUseCase
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract.Event.*
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract.State
import com.patrykkosieradzki.androidmviexample.utils.BaseViewModel
import com.patrykkosieradzki.androidmviexample.utils.UiState
import com.patrykkosieradzki.androidmviexample.utils.successData

class AddEmployeeViewModel(
    private val getGendersUseCase: GetGendersUseCase,
    private val saveEmployeeUseCase: SaveEmployeeUseCase
) :
    BaseViewModel<State, AddEmployeeContract.Event>(
        initialState = UiState.Loading
    ) {

    init {
        loadGenders()
    }

    override fun handleEvent(event: AddEmployeeContract.Event) {
        when (event) {
            is UpdateFormEvent -> handleUpdateFormEvent(event)
            is AddAddressEvent -> handleAddAddressEvent()
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

    private fun handleAddAddressEvent() {
        updateUiSuccessState {
            it.copy(
                address = "",
                addresses = it.addresses.plus(Address(it.address))
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
            if (isValid) {
                safeLaunch {
                    saveEmployeeUseCase(Employee(
                        firstName = firstName,
                        lastName = lastName,
                        age = age,
                        gender = gender,
                        addresses = addresses
                    ))
                    showSnackbar("New employee saved! :)")
                    clearForm()
                }
            } else {
                showSnackbar("Form is not valid")
            }
        }
    }

    private fun loadGenders() {
        safeLaunch {
            val genders = getGendersUseCase()
            updateUiState { UiState.Success(State(genders = genders)) }
        }
    }

    private fun clearForm() {
        updateUiSuccessState {
            it.copy(
                firstName = "",
                lastName = "",
                gender = "",
                address = "",
                addresses = emptyList()
            )
        }
    }
}
