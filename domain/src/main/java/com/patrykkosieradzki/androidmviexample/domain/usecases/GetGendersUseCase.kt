package com.patrykkosieradzki.androidmviexample.domain.usecases

import com.patrykkosieradzki.androidmviexample.domain.model.Gender
import com.patrykkosieradzki.androidmviexample.domain.repositories.EmployeeRepository

interface GetGendersUseCase {
    suspend operator fun invoke(): List<Gender>
}

class GetGendersUseCaseImpl(
    private val employeeRepository: EmployeeRepository
) : GetGendersUseCase {
    override suspend fun invoke(): List<Gender> {
        return employeeRepository.getGenders()
    }
}
