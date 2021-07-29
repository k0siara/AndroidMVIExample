package com.patrykkosieradzki.androidmviexample.ui.features.details

import com.patrykkosieradzki.androidmviexample.utils.BaseViewModel
import com.patrykkosieradzki.androidmviexample.utils.UiState

class EmployeeDetailsViewModel :
    BaseViewModel<EmployeeDetailsContract.State, EmployeeDetailsContract.Event>(
        initialState = UiState.Loading
    ) {
    override fun handleEvent(event: EmployeeDetailsContract.Event) {
    }
}
