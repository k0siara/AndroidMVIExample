package com.patrykkosieradzki.androidmviexample.di

import com.patrykkosieradzki.androidmviexample.ExampleAppConfiguration
import com.patrykkosieradzki.androidmviexample.domain.AppConfiguration
import com.patrykkosieradzki.androidmviexample.domain.usecases.GetGendersUseCase
import com.patrykkosieradzki.androidmviexample.domain.usecases.GetGendersUseCaseImpl
import com.patrykkosieradzki.androidmviexample.domain.usecases.SaveEmployeeUseCase
import com.patrykkosieradzki.androidmviexample.domain.usecases.SaveEmployeeUseCaseImpl
import com.patrykkosieradzki.androidmviexample.ui.features.add.AddEmployeeViewModel
import com.patrykkosieradzki.androidmviexample.ui.features.details.EmployeeDetailsViewModel
import com.patrykkosieradzki.androidmviexample.ui.features.employees.EmployeeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AppConfiguration> {
        ExampleAppConfiguration()
    }

    factory<GetGendersUseCase> {
        GetGendersUseCaseImpl(
            employeeRepository = get()
        )
    }

    factory<SaveEmployeeUseCase> {
        SaveEmployeeUseCaseImpl(
            employeeRepository = get()
        )
    }

    viewModel {
        EmployeeListViewModel(
            employeeDao = get()
        )
    }

    viewModel {
        AddEmployeeViewModel(
            getGendersUseCase = get(),
            saveEmployeeUseCase = get()
        )
    }

    viewModel {
        EmployeeDetailsViewModel()
    }
}
