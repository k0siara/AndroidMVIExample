package com.patrykkosieradzki.androidmviexample.ui

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeScreen
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeViewModel
import com.patrykkosieradzki.androidmviexample.utils.ComposeRobot
import com.patrykkosieradzki.androidmviexample.utils.RobotTest
import com.patrykkosieradzki.androidmviexample.utils.UiState
import org.junit.Rule
import org.junit.Test

class AddEmployeeScreenTest : RobotTest<AddEmployeeScreenTestRobot>() {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun exampleTest() {
        withRobot {
            startScreen()
            wait(1)
            captureAndCompare("AddEmployeeScreen_01")
        }
    }

    override fun createRobot() = AddEmployeeScreenTestRobot(composeTestRule)
}

class AddEmployeeScreenTestRobot(
    composeTestRule: ComposeContentTestRule
) :
    ComposeRobot<AddEmployeeContract.State, AddEmployeeContract.Event, AddEmployeeViewModel>(
        AddEmployeeViewModel::class.java,
        composeTestRule
    ) {

    fun startScreen() {
        createAndSetMockViewModel(initialUiState = UiState.Success(AddEmployeeContract.State()))
        setContent {
            AddEmployeeScreen(viewModel = it)
        }
    }
}