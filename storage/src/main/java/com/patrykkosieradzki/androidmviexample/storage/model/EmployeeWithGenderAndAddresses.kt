package com.patrykkosieradzki.androidmviexample.storage.model

import androidx.room.Embedded
import androidx.room.Relation
import com.patrykkosieradzki.androidmviexample.storage.entity.AddressEntity
import com.patrykkosieradzki.androidmviexample.storage.entity.EmployeeEntity
import com.patrykkosieradzki.androidmviexample.storage.entity.GenderEntity

data class EmployeeWithGenderAndAddresses(
    @Embedded val employee: EmployeeEntity,
    @Embedded val gender: GenderEntity,
    @Relation(
        parentColumn = "employeeId",
        entityColumn = "employeeId"
    )
    val playlists: List<AddressEntity>
)
