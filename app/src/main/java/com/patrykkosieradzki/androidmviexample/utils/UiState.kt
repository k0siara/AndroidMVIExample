package com.patrykkosieradzki.androidmviexample.utils

interface UiState {
    val isLoading: Boolean
}

abstract class UiLoadingState : UiState {
    override val isLoading: Boolean
        get() = true
}

abstract class UiErrorState : UiState {
    override val isLoading: Boolean
        get() = false
}

abstract class UiSuccessState : UiState {
    override val isLoading: Boolean
        get() = false
}