package com.patrykkosieradzki.androidmviexample.ui.employees

import com.patrykkosieradzki.androidmviexample.utils.BaseViewModel

class EmployeeListViewModel :
    BaseViewModel<EmployeeListContract.State, EmployeeListContract.Event, EmployeeListContract.Effect>(
        initialState = EmployeeListContract.State()
    ) {

    override fun handleEvent(event: EmployeeListContract.Event) {
        TODO("Not yet implemented")
    }
}
