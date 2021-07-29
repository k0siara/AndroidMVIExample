package com.patrykkosieradzki.androidmviexample.ui.composables.snackbar

import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.patrykkosieradzki.androidmviexample.ui.composables.lifecycleAwareState
import com.patrykkosieradzki.androidmviexample.utils.delegates.CanDisplaySnackbar

@Composable
fun HandleSnackbarIfSupported(
    lifecycleOwner: LifecycleOwner,
    viewModel: ViewModel,
    scaffoldState: ScaffoldState
) {
    if (viewModel is CanDisplaySnackbar) {
        val snackbarState by lifecycleAwareState(
            lifecycleOwner = lifecycleOwner,
            stateFlow = viewModel.snackbarState,
            initialState = viewModel.initialSnackbarState
        )

        if (snackbarState.isShown) {
            LaunchedEffect(scaffoldState.snackbarHostState) {
                when (scaffoldState.snackbarHostState.showSnackbar(snackbarState.message)) {
                    SnackbarResult.Dismissed -> viewModel.dismissSnackbar()
                    SnackbarResult.ActionPerformed -> {
                    }
                    else -> {
                    }
                }
            }
        }
    }
}
