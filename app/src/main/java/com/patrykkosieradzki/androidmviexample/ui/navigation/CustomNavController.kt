package com.patrykkosieradzki.androidmviexample.ui.navigation

import androidx.navigation.NavHostController

interface CustomNavController {

}

class CustomNavControllerImpl(
    private val navHostController: NavHostController
) : CustomNavController {
}