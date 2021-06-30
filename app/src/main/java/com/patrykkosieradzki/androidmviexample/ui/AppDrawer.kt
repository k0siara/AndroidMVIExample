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
import androidx.navigation.NavController

@Composable
fun AppDrawer(
    currentRoute: String = "",
    navController: NavController
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(Modifier.height(24.dp))
        TextButton(
            onClick = {
                navController.navigate(MyDestination.ADD_EMPLOYEE_PATH)
            }
        ) {
            Text(text = "Add employee")
        }

        TextButton(
            onClick = {
                navController.navigate(MyDestination.EMPLOYEE_LIST_PATH)
            }
        ) {
            Text(text = "Employee list")
        }
    }
}