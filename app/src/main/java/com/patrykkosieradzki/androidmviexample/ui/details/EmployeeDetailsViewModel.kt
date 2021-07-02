package com.patrykkosieradzki.androidmviexample.ui.details

import com.patrykkosieradzki.androidmviexample.utils.BaseComposeViewModel
import com.patrykkosieradzki.androidmviexample.utils.UiState

class EmployeeDetailsViewModel :
    BaseComposeViewModel<EmployeeDetailsContract.State, EmployeeDetailsContract.Event>(
        initialState = UiState.Loading
    ) {
    override fun handleEvent(event: EmployeeDetailsContract.Event) {
    }
}
