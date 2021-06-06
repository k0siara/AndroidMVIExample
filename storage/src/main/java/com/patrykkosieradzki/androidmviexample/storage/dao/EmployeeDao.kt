package com.patrykkosieradzki.androidmviexample.storage.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.patrykkosieradzki.androidmviexample.storage.entity.EmployeeEntity
import com.patrykkosieradzki.androidmviexample.storage.model.EmployeeWithGenderAndAddresses

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employees")
    fun pagingSource(): PagingSource<Long, EmployeeWithGenderAndAddresses>

    @Insert
    fun insertAll(employees: List<EmployeeEntity>)

    @Delete
    fun delete(employeeEntity: EmployeeEntity)
}
