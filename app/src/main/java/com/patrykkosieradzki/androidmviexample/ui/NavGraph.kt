package com.patrykkosieradzki.androidmviexample.ui

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.patrykkosieradzki.androidmviexample.ui.add.AddEmployeeScreen
import com.patrykkosieradzki.androidmviexample.ui.employees.EmployeeListScreen

object MyDestination {
    const val ADD_EMPLOYEE_PATH = "add-employee"
    const val EMPLOYEE_LIST_PATH = "employee-list"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    startDestination: String = MyDestination.ADD_EMPLOYEE_PATH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MyDestination.ADD_EMPLOYEE_PATH) {
            AddEmployeeScreen(scaffoldState = scaffoldState)
        }
        composable(MyDestination.EMPLOYEE_LIST_PATH) {
            EmployeeListScreen(scaffoldState = scaffoldState)
        }
    }
}