package com.patrykkosieradzki.androidmviexample.storage.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.patrykkosieradzki.androidmviexample.storage.entity.EmployeeEntity
import com.patrykkosieradzki.androidmviexample.storage.model.EmployeeWithGenderAndAddresses

@Dao
interface EmployeeDao {
    @Transaction
    @Query("SELECT * FROM employees")
    fun pagingSource(): PagingSource<Int, EmployeeWithGenderAndAddresses>

    @Insert
    suspend fun insertAll(employees: List<EmployeeEntity>)

    @Delete
    suspend fun delete(employeeEntity: EmployeeEntity)
}
