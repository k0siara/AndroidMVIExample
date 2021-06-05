package com.patrykkosieradzki.androidmviexample.utils.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

fun Fragment.navigateTo(directions: NavDirections) {
    findNavController().navigate(directions)
}

val Fragment.appCompatActivity: AppCompatActivity
    get() = requireActivity() as AppCompatActivity
