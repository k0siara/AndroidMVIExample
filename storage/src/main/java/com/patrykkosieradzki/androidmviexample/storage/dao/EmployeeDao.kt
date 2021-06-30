package com.patrykkosieradzki.androidmviexample.storage.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.patrykkosieradzki.androidmviexample.domain.model.Employee
import com.patrykkosieradzki.androidmviexample.storage.entity.AddressEntity
import com.patrykkosieradzki.androidmviexample.storage.entity.EmployeeEntity
import com.patrykkosieradzki.androidmviexample.storage.entity.GenderEntity
import com.patrykkosieradzki.androidmviexample.storage.model.EmployeeWithGenderAndAddresses

@Dao
abstract class EmployeeDao {
    @Transaction
    @Query("SELECT * FROM employees")
    abstract fun pagingSource(): PagingSource<Int, EmployeeWithGenderAndAddresses>

    suspend fun insertDomainEmployee(emp: Employee) {
        val gender = getGenderByName(emp.gender)
        val employeeId = insertEmployee(
            EmployeeEntity(
                firstName = emp.firstName,
                lastName = emp.lastName,
                age = emp.age,
                genderId = gender?.uid
            )
        )
        insertAllAddresses(emp.addresses.map {
            AddressEntity(
                address = it.name,
                employeeId = employeeId
            )
        })
    }

    @Insert
    abstract suspend fun insertEmployee(employee: EmployeeEntity): Long

    @Insert
    abstract suspend fun insertAllEmployees(employees: List<EmployeeEntity>)

    @Insert
    abstract suspend fun insertAllAddresses(addresses: List<AddressEntity>)

    @Delete
    abstract suspend fun delete(employeeEntity: EmployeeEntity)

    @Insert
    abstract suspend fun insertAllGenders(genders: List<GenderEntity>)

    @Query("SELECT * FROM genders WHERE genders.name = :genderName")
    abstract suspend fun getGenderByName(genderName: String): GenderEntity?

    @Query("SELECT * FROM genders")
    abstract suspend fun getAllGenders(): List<GenderEntity>
}
