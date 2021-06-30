package com.patrykkosieradzki.androidmviexample.domain.repositories

import com.patrykkosieradzki.androidmviexample.domain.model.Employee
import com.patrykkosieradzki.androidmviexample.domain.model.Gender

interface EmployeeRepository {
    suspend fun getGenders(): List<Gender>
    suspend fun saveEmployee(employee: Employee)
}
