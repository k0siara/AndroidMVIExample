package com.patrykkosieradzki.androidmviexample.storage.repositories

import com.patrykkosieradzki.androidmviexample.domain.model.Employee
import com.patrykkosieradzki.androidmviexample.domain.model.Gender
import com.patrykkosieradzki.androidmviexample.domain.repositories.EmployeeRepository
import com.patrykkosieradzki.androidmviexample.storage.dao.EmployeeDao

class LocalEmployeeRepository(
    private val employeeDao: EmployeeDao
) : EmployeeRepository {
    override suspend fun getGenders(): List<Gender> {
        return employeeDao.getAllGenders().map { Gender(it.uid, it.name) }
    }

    override suspend fun saveEmployee(employee: Employee) {
        employeeDao.insertDomainEmployee(employee)
    }
}
