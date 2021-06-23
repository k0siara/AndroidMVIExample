package com.patrykkosieradzki.androidmviexample.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle

@Composable
fun <STATE : UiState, EVENT : UiEvent, EFFECT : UiEffect> BaseComposeScreen(
    viewModel: BaseComposeViewModel<STATE, EVENT, EFFECT>,
    child: @Composable (state: STATE, eventHandler: (EVENT) -> Unit) -> Unit
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val locationFlowLifecycleAware = remember(viewModel.uiState, lifecycleOwner) {
        viewModel.uiState.flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.STARTED)
    }
    val state by locationFlowLifecycleAware.collectAsState(viewModel.initialState)

    child.invoke(state, viewModel.eventHandler)
}