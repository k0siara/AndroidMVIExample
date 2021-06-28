package com.patrykkosieradzki.androidmviexample.ui.add

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.patrykkosieradzki.androidmviexample.ui.add.components.AddEmployeeScreenBody
import com.patrykkosieradzki.androidmviexample.utils.BaseComposeScreen
import com.patrykkosieradzki.androidmviexample.utils.asSuccess
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun AddEmployeeScreen(
    viewModel: AddEmployeeViewModel = getViewModel()
) {
    BaseComposeScreen(viewModel) { state, eventHandler ->
        Scaffold {
            AddEmployeeScreenBody(
                state = state.asSuccess,
                eventHandler = eventHandler
            )
        }
    }
}
