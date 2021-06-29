package com.patrykkosieradzki.androidmviexample.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.google.android.material.snackbar.Snackbar
import com.hadilq.liveevent.LiveEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import timber.log.Timber

@AllOpen
abstract class BaseComposeViewModel<STATE, EVENT : UiEvent, EFFECT : UiEffect>(
    final val initialState: UiState<STATE> = UiState.Loading,
    final val initialSnackbarState: SnackbarState = SnackbarState()
) : ViewModel() {

    // Get Current State
    val currentState: UiState<STATE>
        get() = uiState.value

    val currentSnackbarState: SnackbarState
        get() = snackbarState.value

    private val _uiState: MutableStateFlow<UiState<STATE>> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _snackbarState: MutableStateFlow<SnackbarState> =
        MutableStateFlow(initialSnackbarState)
    val snackbarState = _snackbarState.asStateFlow()

    val eventHandler: (EVENT) -> Unit = ::handleEvent

    private val _navigationCommandEvent = Channel<NavigationCommand>(Channel.BUFFERED)
    val navigationCommandEvent = _navigationCommandEvent.receiveAsFlow()

    protected val handler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception, COROUTINE_EXCEPTION_HANDLER_MESSAGE)
    }

    abstract fun handleEvent(event: EVENT)

    protected fun updateUiState(update: (UiState<STATE>) -> UiState<STATE>) {
        val newState = update(currentState)
        if (newState != currentState) {
            _uiState.value = newState
        }
    }

    protected fun updateUiSuccessState(update: (STATE) -> STATE) {
        val newStateData = update(currentState.successData)
        if (newStateData != currentState.successData) {
            _uiState.value = UiState.Success(newStateData)
        }
    }

    fun showSnackbar(message: String) {
        _snackbarState.value = currentSnackbarState.copy(isShown = true, message = message)
    }

    fun dismissSnackbar() {
        _snackbarState.value = currentSnackbarState.copy(isShown = false)
    }

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }

    companion object {
        private const val COROUTINE_EXCEPTION_HANDLER_MESSAGE = "ExceptionHandler"
    }
}
