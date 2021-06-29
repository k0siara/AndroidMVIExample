package com.patrykkosieradzki.androidmviexample.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeScreen

object MyDestination {
    const val ADD_EMPLOYEE_PATH = "add-employee"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    startDestination: String = MyDestination.ADD_EMPLOYEE_PATH
) {

    NavHost(
        navController = navController,
        startDestination = MyDestination.ADD_EMPLOYEE_PATH
    ) {
        composable(MyDestination.ADD_EMPLOYEE_PATH) {
            AddEmployeeScreen(scaffoldState = scaffoldState)
        }
    }
}