package com.patrykkosieradzki.androidmviexample.domain.usecases

import com.patrykkosieradzki.androidmviexample.domain.model.Employee
import com.patrykkosieradzki.androidmviexample.domain.repositories.EmployeeRepository

interface SaveEmployeeUseCase {
    suspend operator fun invoke(employee: Employee)
}

class SaveEmployeeUseCaseImpl(
    private val employeeRepository: EmployeeRepository
) : SaveEmployeeUseCase {
    override suspend fun invoke(employee: Employee) {
        employeeRepository.saveEmployee(employee)
    }
}