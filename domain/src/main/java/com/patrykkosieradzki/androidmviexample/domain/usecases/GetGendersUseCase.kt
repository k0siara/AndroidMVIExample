package com.patrykkosieradzki.androidmviexample.domain.usecases

import com.patrykkosieradzki.androidmviexample.domain.model.Gender
import com.patrykkosieradzki.androidmviexample.domain.repositories.EmployeeRepository
import kotlinx.coroutines.flow.Flow

interface GetGendersUseCase {
    suspend fun invoke(): List<Gender>
}

class GetGendersUseCaseImpl(
    private val employeeRepository: EmployeeRepository
) : GetGendersUseCase {
    override suspend fun invoke(): List<Gender> {
        return employeeRepository.getGenders()
    }
}
