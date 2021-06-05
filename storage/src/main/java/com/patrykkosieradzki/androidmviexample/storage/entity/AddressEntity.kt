package com.patrykkosieradzki.androidmviexample.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AddressEntity(
    @PrimaryKey val uid: Long,
    val address: String,
    val employeeId: Long
)
