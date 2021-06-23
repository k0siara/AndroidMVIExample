package com.patrykkosieradzki.androidmviexample.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AppDrawer(
    currentRoute: String = "",
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.height(24.dp))
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