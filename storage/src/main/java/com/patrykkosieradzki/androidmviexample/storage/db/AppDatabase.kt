package com.patrykkosieradzki.androidmviexample.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.patrykkosieradzki.androidmviexample.storage.dao.AddressDao
import com.patrykkosieradzki.androidmviexample.storage.dao.EmployeeDao
import com.patrykkosieradzki.androidmviexample.storage.dao.GenderDao
import com.patrykkosieradzki.androidmviexample.storage.entity.AddressEntity
import com.patrykkosieradzki.androidmviexample.storage.entity.EmployeeEntity
import com.patrykkosieradzki.androidmviexample.storage.entity.GenderEntity

@Database(
    entities = [GenderEntity::class, AddressEntity::class, EmployeeEntity::class],
    exportSchema = false,
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun addressDao(): AddressDao
    abstract fun genderDao(): GenderDao
    abstract fun employeeDao(): EmployeeDao
}
