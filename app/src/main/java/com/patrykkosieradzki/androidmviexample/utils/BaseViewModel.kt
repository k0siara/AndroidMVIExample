package com.patrykkosieradzki.androidmviexample.utils

import androidx.lifecycle.*
import androidx.navigation.NavDirections
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

@AllOpen
abstract class BaseViewModel<STATE, EVENT : UiEvent, EFFECT : UiEffect>(
    initialState: UiState<STATE> = UiState.Loading
) : ViewModel() {

    // Get Current State
    val currentState: UiState<STATE>
        get() = uiState.value
    val inProgress: Flow<Boolean>
        get() = uiState.map { it is UiState.Loading }

    private val _uiState: MutableStateFlow<UiState<STATE>> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<EVENT> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: Channel<EFFECT> = Channel()
    val effect = _effect.receiveAsFlow()

    private val _navigationCommandEvent = Channel<NavigationCommand>(Channel.BUFFERED)
    val navigationCommandEvent = _navigationCommandEvent.receiveAsFlow()

    protected val handler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception, COROUTINE_EXCEPTION_HANDLER_MESSAGE)
    }

    open fun initialize() {}

    protected fun navigateTo(navDirections: NavDirections) {
        safeLaunch {
            _navigationCommandEvent.send(NavigationCommand.To(navDirections))
        }
    }

    abstract fun handleEvent(event: EVENT)

    protected fun setUiEvent(newEvent: EVENT) {
        viewModelScope.launch { _event.emit(newEvent) }
    }

    protected fun updateUiState(update: (UiState<STATE>) -> UiState<STATE>) {
        val newState = update(currentState)
        if (newState != currentState) {
            _uiState.value = newState
        }
    }

    protected fun setEffect(effectBuilder: () -> EFFECT) {
        viewModelScope.launch { _effect.send(effectBuilder()) }
    }

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }

    companion object {
        private const val COROUTINE_EXCEPTION_HANDLER_MESSAGE = "ExceptionHandler"
    }
}
