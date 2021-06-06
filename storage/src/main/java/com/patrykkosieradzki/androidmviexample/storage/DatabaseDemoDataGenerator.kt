package com.patrykkosieradzki.androidmviexample.storage

import com.patrykkosieradzki.androidmviexample.domain.DemoDataGenerator
import com.patrykkosieradzki.androidmviexample.storage.dao.AddressDao
import com.patrykkosieradzki.androidmviexample.storage.dao.EmployeeDao
import com.patrykkosieradzki.androidmviexample.storage.dao.GenderDao
import com.patrykkosieradzki.androidmviexample.storage.entity.AddressEntity
import com.patrykkosieradzki.androidmviexample.storage.entity.EmployeeEntity
import com.patrykkosieradzki.androidmviexample.storage.entity.GenderEntity

class DatabaseDemoDataGenerator(
    private val genderDao: GenderDao,
    private val addressDao: AddressDao,
    private val employeeDao: EmployeeDao
) : DemoDataGenerator {

    override suspend fun loadDemoDataIntoDB() {
        genderDao.insertAll(GENDERS)
        employeeDao.insertAll(EMPLOYEES)
        addressDao.insertAll(ADDRESSES)
    }

    companion object {
        val ADDRESSES = listOf(
            AddressEntity(1, "Some street 1", 1),
            AddressEntity(2, "Some street 2", 1),
            AddressEntity(3, "Some street 3", 2),
            AddressEntity(4, "Some street 4", 3),
        )

        val GENDERS = listOf(
            GenderEntity(1, "Man"),
            GenderEntity(2, "Woman"),
        )

        val EMPLOYEES = listOf(
            EmployeeEntity(1, "John", "Snow", 30, 1),
            EmployeeEntity(2, "Ann", "Brie", 25, 2),
        )
    }
}
