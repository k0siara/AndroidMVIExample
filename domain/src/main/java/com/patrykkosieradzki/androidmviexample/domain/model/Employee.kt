package com.patrykkosieradzki.androidmviexample.domain.model

data class Employee(
    val employeeId: Long? = null,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val gender: String,
    val addresses: List<Address>
)