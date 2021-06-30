package com.patrykkosieradzki.androidmviexample.storage.di

import androidx.room.Room
import com.patrykkosieradzki.androidmviexample.domain.DemoDataGenerator
import com.patrykkosieradzki.androidmviexample.domain.repositories.EmployeeRepository
import com.patrykkosieradzki.androidmviexample.storage.DatabaseDemoDataGenerator
import com.patrykkosieradzki.androidmviexample.storage.db.AppDatabase
import com.patrykkosieradzki.androidmviexample.storage.repositories.LocalEmployeeRepository
import org.koin.dsl.module

val storageModule = module {
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java, "employee_db"
        ).build()
    }

    single { get<AppDatabase>().employeeDao() }

    single<DemoDataGenerator> {
        DatabaseDemoDataGenerator(
            employeeDao = get()
        )
    }

    single<EmployeeRepository> {
        LocalEmployeeRepository(
            employeeDao = get(),
        )
    }
}
