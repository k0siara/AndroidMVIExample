package com.patrykkosieradzki.androidmviexample.ui

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
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
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun exampleTest() {
        withRobot {
            startScreen()
            wait(1)
            // screenshot
        }
    }

    override fun createRobot() = AddEmployeeScreenTestRobot(composeTestRule)
}

class AddEmployeeScreenTestRobot(
    composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) :
    ComposeRobot<AddEmployeeContract.State, AddEmployeeContract.Event, AddEmployeeViewModel, MainActivity>(
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