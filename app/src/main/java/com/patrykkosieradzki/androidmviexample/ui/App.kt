package com.patrykkosieradzki.androidmviexample.ui

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.patrykkosieradzki.androidmviexample.ui.navigation.CustomNavControllerImpl
import com.patrykkosieradzki.androidmviexample.ui.theme.AppTheme

@Preview
@Composable
fun App() {
    AppTheme {
        ProvideWindowInsets {
            val navController = rememberNavController()
            val customNavController = remember {
                CustomNavControllerImpl(navController)
            }

            val scaffoldState = rememberScaffoldState()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute =
                navBackStackEntry?.destination?.route ?: MyDestination.ADD_EMPLOYEE_PATH

            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                        title = { Text(getTitleForRoute(currentRoute)) }
                    )
                },
                drawerContent = {
                    AppDrawer(
                        currentRoute = getTitleForRoute(currentRoute),
                    )
                }
            ) {
                NavGraph(
                    navController = navController,
                    scaffoldState = scaffoldState,
                )
            }
        }
    }
}

fun getTitleForRoute(route: String): String {
    return when (route) {
        "add-employee" -> "Add Employee"
        else -> "Unknown route"
    }
}