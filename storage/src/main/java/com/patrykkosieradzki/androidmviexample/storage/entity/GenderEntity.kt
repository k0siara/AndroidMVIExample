package com.patrykkosieradzki.androidmviexample.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genders")
data class GenderEntity(
    @PrimaryKey val uid: Long,
    @ColumnInfo(name = "name") val name: String
)
