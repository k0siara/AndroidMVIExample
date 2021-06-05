package com.patrykkosieradzki.androidmviexample.storage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.patrykkosieradzki.androidmviexample.storage.entity.EmployeeEntity

@Dao
interface EmployeeDao {
//    @Query("SELECT * FROM employees")
//    fun pagingSource(): PagingSource<Int, EmployeeEntity>

    @Insert
    fun insertAll(employees: List<EmployeeEntity>)

    @Delete
    fun delete(employeeEntity: EmployeeEntity)
}
