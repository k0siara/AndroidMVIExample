package com.patrykkosieradzki.androidmviexample.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import com.patrykkosieradzki.androidmviexample.storage.entity.AddressEntity

@Dao
interface AddressDao {
    @Insert
    fun insertAll(addresses: List<AddressEntity>)
}
