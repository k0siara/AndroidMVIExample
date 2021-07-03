package com.patrykkosieradzki.androidmviexample.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onRoot
import com.nhaarman.mockitokotlin2.whenever
import com.patrykkosieradzki.androidmviexample.ui.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import org.mockito.Mockito.mock

open class ComposeRobot<STATE, EVENT : UiEvent, VM : BaseComposeViewModel<STATE, EVENT>>(
    private val clazz: Class<VM>,
    private val composeTestRule: ComposeContentTestRule
) : Robot() {
    private lateinit var viewModel: VM

    fun createAndSetMockViewModel(
        initialUiState: UiState<STATE> = UiState.Loading,
        snackbarState: SnackbarState = SnackbarState()
    ) {
        viewModel = mock(clazz, BaseComposeViewModelDefaultAnswer<EVENT>(initialUiState))
        whenever(viewModel.initialState).thenReturn(initialUiState)
        whenever(viewModel.initialSnackbarState).thenReturn(snackbarState)
    }

    fun setViewModel(viewModel: VM) {
        this.viewModel = viewModel
    }

    fun setContent(content: @Composable (viewModel: VM) -> Unit) {
        composeTestRule.setContent {
            AppTheme {
                content(viewModel)
            }
        }
    }

    fun onViewModel(actions: VM.() -> Unit) {
        if (::viewModel.isInitialized) {
            actions(viewModel)
        }
    }

    fun setUiState(newUiState: UiState<STATE>) {
        onViewModel {
            val currentUiState = uiState as MutableStateFlow
            currentUiState.value = newUiState
        }
    }

    fun captureAndCompare(screenshotName: String) {
        captureAndCompare(
            screenshotName = screenshotName,
            node = composeTestRule.onRoot()
        )
    }
}