package com.patrykkosieradzki.androidmviexample.domain.repositories

import com.patrykkosieradzki.androidmviexample.domain.model.Gender

interface EmployeeRepository {
    suspend fun getGenders(): List<Gender>
}
