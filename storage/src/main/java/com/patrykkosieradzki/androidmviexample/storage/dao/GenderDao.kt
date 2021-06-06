package com.patrykkosieradzki.androidmviexample.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import com.patrykkosieradzki.androidmviexample.storage.entity.GenderEntity

@Dao
interface GenderDao {
    @Insert
    suspend fun insertAll(genders: List<GenderEntity>)
}
