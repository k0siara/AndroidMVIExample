package com.patrykkosieradzki.androidmviexample.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeScreen
import com.patrykkosieradzki.androidmviexample.ui.theme.AppTheme

@Composable
fun App() {
    AppTheme {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState  = scaffoldState,
            drawerContent = {
                AppDrawer()
            }
        ) {
            AddEmployeeScreen()
        }
    }
}
