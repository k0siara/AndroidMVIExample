package com.patrykkosieradzki.androidmviexample.utils

import androidx.lifecycle.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import timber.log.Timber

@AllOpen
abstract class BaseViewModel<STATE : UiState, EVENT : UiEvent, EFFECT : UiEffect>(
    private val initialState: STATE
) : ViewModel() {

    // Get Current State
    val currentState: STATE
        get() = uiState.value
    val inProgress: Flow<Boolean>
        get() = uiState.map { it.inProgress }

    private val _uiState: MutableStateFlow<STATE> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<EVENT> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: Channel<EFFECT> = Channel()
    val effect = _effect.receiveAsFlow()

    protected val handler = CoroutineExceptionHandler { _, exception ->
        Timber.e(exception, COROUTINE_EXCEPTION_HANDLER_MESSAGE)
//        val exceptionToShow = updateError(exception)
//        if (exceptionToShow.throwable != null) {
// //            showErrorEvent.fireEvent(exceptionToShow)
//        }
    }

    open fun initialize() {
    }

    private fun subscribeToEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }

    abstract fun handleEvent(event: EVENT)

//    protected open fun updateError(exception: Throwable): ErrorEvent {
//        return ErrorEvent(exception, isInitialState && _viewState.valueNN.inProgress)
//    }

    protected fun setUiEvent(newEvent: EVENT) {
        viewModelScope.launch { _event.emit(newEvent) }
    }

    protected fun updateUiState(update: (STATE) -> STATE) {
        val newState = update(currentState)
        if (newState != currentState) {
            _uiState.value = newState
        }
    }

    protected fun setEffect(effectBuilder: () -> EFFECT) {
        viewModelScope.launch { _effect.send(effectBuilder()) }
    }

    fun updateUiStateToSuccess() {
        updateUiState {
            @Suppress("UNCHECKED_CAST")
            it.toSuccess() as STATE
        }
    }

    protected fun safeLaunch(block: suspend CoroutineScope.() -> Unit) {
        viewModelScope.launch(handler, block = block)
    }

//    protected fun showToast(text: String) {
//        showToastEvent.fireEvent(text)
//    }

    companion object {
        private const val COROUTINE_EXCEPTION_HANDLER_MESSAGE = "ExceptionHandler"
    }
}

data class ErrorEvent(
    val throwable: Throwable? = null,
    val isInitialState: Boolean = false
)
