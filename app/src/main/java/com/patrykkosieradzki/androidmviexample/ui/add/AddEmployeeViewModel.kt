package com.patrykkosieradzki.androidmviexample.ui.add

import com.patrykkosieradzki.androidmviexample.utils.BaseViewModel

class AddEmployeeViewModel :
    BaseViewModel<AddEmployeeContract.State, AddEmployeeContract.Event, AddEmployeeContract.Effect>(
        initialState = AddEmployeeContract.State.Loading()
    ) {
    override fun handleEvent(event: AddEmployeeContract.Event) {
    }
}
