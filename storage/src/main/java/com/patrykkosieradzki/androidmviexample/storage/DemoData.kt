package com.patrykkosieradzki.androidmviexample.storage

import com.patrykkosieradzki.androidmviexample.storage.entity.AddressEntity
import com.patrykkosieradzki.androidmviexample.storage.entity.EmployeeEntity
import com.patrykkosieradzki.androidmviexample.storage.entity.GenderEntity

interface DemoData {
    companion object {
        fun getAddresses() = listOf(
            AddressEntity(1, "Some street 1", 1),
            AddressEntity(2, "Some street 2", 1),
            AddressEntity(3, "Some street 3", 2),
            AddressEntity(4, "Some street 4", 3),
        )

        fun getGenders() = listOf(
            GenderEntity(1, "Man"),
            GenderEntity(2, "Woman"),
        )

        fun getEmployees(): List<EmployeeEntity> = listOf(
            EmployeeEntity(1, "John", "Snow", 30, 1),
            EmployeeEntity(1, "Ann", "Brie", 25, 2),
        )
    }
}
