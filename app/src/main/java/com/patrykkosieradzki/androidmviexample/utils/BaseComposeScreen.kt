package com.patrykkosieradzki.androidmviexample.utils

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import com.patrykkosieradzki.androidmviexample.ui.composables.CenteredCircularProgressIndicator

@Composable
fun <STATE, EVENT : UiEvent, EFFECT : UiEffect> BaseComposeScreen(
    viewModel: BaseComposeViewModel<STATE, EVENT, EFFECT>,
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
    child: @Composable (state: UiState<STATE>, eventHandler: (EVENT) -> Unit) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val locationFlowLifecycleAware = remember(viewModel.uiState, lifecycleOwner) {
        viewModel.uiState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by locationFlowLifecycleAware.collectAsState(viewModel.initialState)

    when (state) {
        is UiState.Loading -> renderOnLoading.invoke(viewModel.eventHandler)
        is UiState.Success -> child.invoke(state, viewModel.eventHandler)
        is UiState.Failure -> renderOnFailure.invoke(viewModel.eventHandler)
//        Retrying -> // a different loader
//        SwipeRefreshing -> // show swipe refresh loader
//        SwipeRefreshingFailure -> // show error
        else -> renderOnLoading.invoke(viewModel.eventHandler)
    }
}