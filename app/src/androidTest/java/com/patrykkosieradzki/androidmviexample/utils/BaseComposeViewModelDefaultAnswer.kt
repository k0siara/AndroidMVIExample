package com.patrykkosieradzki.androidmviexample.utils

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.mockito.Answers
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer

class BaseComposeViewModelDefaultAnswer<EVENT : UiEvent>(
    defaultViewState: UiState<*> = UiState.Loading,
    defaultSnackbarState: SnackbarState = SnackbarState()
) : Answer<Any> {
    private val uiState = MutableStateFlow(defaultViewState)
    private val snackbarState = MutableStateFlow(defaultSnackbarState)
    private val eventHandler: (EVENT) -> Unit = {}

    override fun answer(invocation: InvocationOnMock?): Any? {
        return when (invocation!!.method.returnType) {
            StateFlow::class.java -> when (invocation.method.name) {
                "getUiState" -> uiState
                "getSnackbarState" -> snackbarState
                else -> Answers.RETURNS_DEFAULTS.answer(invocation)
            }
            Function1::class.java -> when (invocation.method.name) {
                "getEventHandler" -> eventHandler
                else -> Answers.RETURNS_DEFAULTS.answer(invocation)
            }
            else -> Answers.RETURNS_DEFAULTS.answer(invocation)
        }
    }
}