package com.patrykkosieradzki.androidmviexample.ui.composables

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import kotlinx.coroutines.flow.StateFlow

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