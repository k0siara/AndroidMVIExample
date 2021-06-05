package com.patrykkosieradzki.androidmviexample.di

import com.patrykkosieradzki.androidmviexample.ExampleAppConfiguration
import com.patrykkosieradzki.androidmviexample.domain.AppConfiguration
import com.patrykkosieradzki.androidmviexample.ui.employees.EmployeeListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<AppConfiguration> {
        ExampleAppConfiguration()
    }

//    factory<GetAllStationsUseCase> {
//        GetAllStationsUseCaseImpl(
//            flightRepository = get()
//        )
//    }
//

    viewModel {
        EmployeeListViewModel()
    }
}
