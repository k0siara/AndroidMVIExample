package com.patrykkosieradzki.androidmviexample.ui.details

import com.patrykkosieradzki.androidmviexample.utils.BaseViewModel

class EmployeeDetailsViewModel :
    BaseViewModel<EmployeeDetailsContract.State, EmployeeDetailsContract.Event, EmployeeDetailsContract.Effect>(
        initialState = EmployeeDetailsContract.State.Loading()
    ) {
    override fun handleEvent(event: EmployeeDetailsContract.Event) {
    }
}
