package com.patrykkosieradzki.androidmviexample.storage.repositories

import com.patrykkosieradzki.androidmviexample.domain.repositories.EmployeeRepository
import com.patrykkosieradzki.androidmviexample.storage.dao.EmployeeDao

class LocalEmployeeRepository(private val employeeDao: EmployeeDao) : EmployeeRepository {

}