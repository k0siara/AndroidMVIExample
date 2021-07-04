package com.patrykkosieradzki.androidmviexample.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import com.nhaarman.mockitokotlin2.whenever
import com.patrykkosieradzki.androidmviexample.ui.theme.AppTheme
import org.mockito.Mockito.mock

open class ComposeRobot<STATE, EVENT : UiEvent, VM : BaseComposeViewModel<STATE, EVENT>>(
    private val clazz: Class<VM>,
    val composeTestRule: ComposeContentTestRule
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

    fun setViewModel(viewModel: VM, initialUiState: UiState<STATE>) {
        this.viewModel = viewModel
        setUiState(initialUiState)
    }

    fun setContent(content: @Composable (viewModel: VM) -> Unit) {
        composeTestRule.setContent {
            AppTheme {
                content(viewModel)
            }
        }
    }

    fun onViewModel(actions: VM.() -> Unit) {
        composeTestRule.runOnUiThread {
            if (::viewModel.isInitialized) {
                actions(viewModel)
            }
        }
    }

    fun setUiState(newUiState: UiState<STATE>) {
        onViewModel {
            setUiState {
                newUiState
            }
        }
    }

    fun capture(screenshotName: String) {
        captureAndCompare(
            screenshotName = screenshotName,
            node = composeTestRule.onRoot()
        )
    }
}

fun ComposeRobot<*, *, *>.assertTextDisplayed(text: String) {
    composeTestRule.onNodeWithText(text).assertIsDisplayed()
}
