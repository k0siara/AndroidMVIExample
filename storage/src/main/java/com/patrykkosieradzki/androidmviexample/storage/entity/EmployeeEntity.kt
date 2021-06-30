package com.patrykkosieradzki.androidmviexample.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employees")
data class EmployeeEntity(
    @PrimaryKey val employeeId: Long? = null,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val genderId: Long?,
)
