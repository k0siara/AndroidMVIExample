package com.patrykkosieradzki.androidmviexample.utils

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import com.patrykkosieradzki.androidmviexample.ui.composables.CenteredCircularProgressIndicator
import kotlinx.coroutines.flow.StateFlow

@Composable
fun <STATE, EVENT : UiEvent, VM : BaseComposeViewModel<STATE, EVENT>> BaseComposeScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    viewModel: VM,
    renderOnLoading: @Composable (eventHandler: (EVENT) -> Unit) -> Unit = {
        Scaffold {
            CenteredCircularProgressIndicator()
        }
    },
    renderOnFailure: @Composable (eventHandler: (EVENT) -> Unit) -> Unit = {
        Scaffold {
            Text("Error occurred")
        }
    },
    renderOnSuccess: @Composable (state: UiState<STATE>, eventHandler: (EVENT) -> Unit) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val state by lifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        stateFlow = viewModel.uiState,
        initialState = viewModel.initialState
    )
    val snackbarState by lifecycleAwareState(
        lifecycleOwner = lifecycleOwner,
        stateFlow = viewModel.snackbarState,
        initialState = viewModel.initialSnackbarState
    )

    when (state) {
        is UiState.Loading -> renderOnLoading.invoke(viewModel.eventHandler)
        is UiState.Success -> {
            renderOnSuccess.invoke(state, viewModel.eventHandler)
        }
        is UiState.Failure -> renderOnFailure.invoke(viewModel.eventHandler)
//        Retrying -> // a different loader
//        SwipeRefreshing -> // show swipe refresh loader
//        SwipeRefreshingFailure -> // show error
        else -> renderOnLoading.invoke(viewModel.eventHandler)
    }

    if (snackbarState.isShown) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            val snackbarResult = scaffoldState.snackbarHostState.showSnackbar(snackbarState.message)

            when (snackbarResult) {
                SnackbarResult.Dismissed -> viewModel.dismissSnackbar()
                SnackbarResult.ActionPerformed -> {}
                else -> {}
            }
        }
    }
}

@Composable
fun <T> lifecycleAwareState(
    lifecycleOwner: LifecycleOwner,
    stateFlow: StateFlow<T>,
    initialState: T
): State<T> {
    val lifecycleAwareStateFlow = remember(stateFlow, lifecycleOwner) {
        stateFlow.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    return lifecycleAwareStateFlow.collectAsState(initialState)
}