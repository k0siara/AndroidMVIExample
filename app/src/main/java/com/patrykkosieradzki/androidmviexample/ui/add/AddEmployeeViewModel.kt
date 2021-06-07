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

    override fun handleEvent(event: AddEmployeeContract.Event) {}

    private fun loadGenders() {
        safeLaunch {
            val genders = employeeRepository.getGenders()
            updateUiState { AddEmployeeContract.State.Initial(genders) }
        }
    }

    fun onAddAddressClicked() {

    }

    fun onRemoveAddressClicked(address: Address) {

    }
}
