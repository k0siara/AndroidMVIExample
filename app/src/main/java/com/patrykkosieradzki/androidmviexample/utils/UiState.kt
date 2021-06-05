package com.patrykkosieradzki.androidmviexample.utils

interface UiState {
    val inProgress: Boolean
    fun toSuccess(): UiState
}
