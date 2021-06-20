package com.patrykkosieradzki.androidmviexample.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AppDrawer() {
    Column(modifier = Modifier.fillMaxSize()) {
        TextButton(
            onClick = {}
        ) {
            Text(text = "Home")
        }

        TextButton(
            onClick = {}
        ) {
            Text(text = "Second button")
        }
    }
}