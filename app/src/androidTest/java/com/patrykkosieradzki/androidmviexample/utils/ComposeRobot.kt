package com.patrykkosieradzki.androidmviexample.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.test.ext.junit.rules.ActivityScenarioRule

open class ComposeRobot<STATE, EVENT : UiEvent, VM : BaseComposeViewModel<STATE, EVENT>>(
    private val composeTestRule: AndroidComposeTestRule<ActivityScenarioRule<*>, *>
) : Robot() {
    fun setContent(content: @Composable () -> Unit) {
        composeTestRule.setContent(content)
    }
}