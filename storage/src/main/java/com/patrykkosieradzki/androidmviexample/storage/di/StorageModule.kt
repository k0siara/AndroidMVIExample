package com.patrykkosieradzki.androidmviexample.storage.di

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.patrykkosieradzki.androidmviexample.domain.repositories.EmployeeRepository
import com.patrykkosieradzki.androidmviexample.storage.db.AppDatabase
import com.patrykkosieradzki.androidmviexample.storage.repositories.LocalEmployeeRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val storageModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, "employee_db"
        ).addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
//                Executors.newSingleThreadExecutor().execute {
//                    get<AppDatabase>().genderDao().insertAll(DemoData.getGenders())
//                    get<AppDatabase>().employeeDao().insertAll(DemoData.getEmployees())
//                    get<AppDatabase>().addressDao().insertAll(DemoData.getAddresses())
//                }
            }
        }).build()
    }

    single {
        get<AppDatabase>().employeeDao()
    }

    single<EmployeeRepository> {
        LocalEmployeeRepository(employeeDao = get())
    }
}
