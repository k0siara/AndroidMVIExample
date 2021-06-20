package com.patrykkosieradzki.androidmviexample.ui.add

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun AddEmployeeScreen() {
    Scaffold {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Hi!")
            Text(text = "Something!")
        }
    }
}