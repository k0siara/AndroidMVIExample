package com.patrykkosieradzki.androidmviexample.ui.add

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.patrykkosieradzki.androidmviexample.ui.add.components.AddEmployeeScreenBody
import com.patrykkosieradzki.androidmviexample.utils.BaseComposeScreen
import com.patrykkosieradzki.androidmviexample.utils.successData
import org.koin.androidx.compose.getViewModel

@Preview
@Composable
fun AddEmployeeScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: AddEmployeeViewModel = getViewModel()
) {
    BaseComposeScreen(
        scaffoldState = scaffoldState,
        viewModel = viewModel
    ) { state, eventHandler ->
        Scaffold {
            AddEmployeeScreenBody(
                state = state.successData,
                eventHandler = eventHandler
            )
        }
    }
}
