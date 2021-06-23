package com.patrykkosieradzki.androidmviexample.ui.add

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract.State.*
import com.patrykkosieradzki.androidmviexample.ui.add.components.AddEmployeeScreenBody
import com.patrykkosieradzki.androidmviexample.ui.composables.CenteredCircularProgressIndicator
import com.patrykkosieradzki.androidmviexample.utils.BaseComposeScreen
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun AddEmployeeScreen(
    viewModel: AddEmployeeComposeViewModel = getViewModel()
) {
    BaseComposeScreen(viewModel) { state, eventHandler ->
        Scaffold {
            when (state) {
                is Initial, is FormUpdated -> {
                    AddEmployeeScreenBody(
                        state = state,
                        eventHandler = eventHandler
                    )
                }
                is Loading -> {
                    CenteredCircularProgressIndicator()
                }
            }
        }
    }
}
