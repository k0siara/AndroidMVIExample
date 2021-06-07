package com.patrykkosieradzki.androidmviexample.storage.repositories

import com.patrykkosieradzki.androidmviexample.domain.model.Gender
import com.patrykkosieradzki.androidmviexample.domain.repositories.EmployeeRepository
import com.patrykkosieradzki.androidmviexample.storage.dao.EmployeeDao
import com.patrykkosieradzki.androidmviexample.storage.dao.GenderDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalEmployeeRepository(
    private val genderDao: GenderDao,
    private val employeeDao: EmployeeDao
) : EmployeeRepository {
    override suspend fun getGenders(): List<Gender> {
        return genderDao.getAll().map { Gender(it.uid, it.name) }
    }
}
