package com.patrykkosieradzki.androidmviexample.ui

import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeContract
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeViewModel
import com.patrykkosieradzki.androidmviexample.utils.ComposeRobot
import com.patrykkosieradzki.androidmviexample.utils.RobotTest
import org.junit.Rule
import org.junit.Test

class AddEmployeeScreenTest : RobotTest<AddEmployeeScreenTestRobot>() {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun exampleTest() {
        withRobot {

        }
    }

    override fun createRobot() = AddEmployeeScreenTestRobot(composeTestRule)
}

class AddEmployeeScreenTestRobot(
    composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<MainActivity>, MainActivity>
) :
    ComposeRobot<AddEmployeeContract.State, AddEmployeeContract.Event, AddEmployeeViewModel>(
        composeTestRule
    ) {

}