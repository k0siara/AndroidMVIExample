package com.patrykkosieradzki.androidmviexample.storage.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.patrykkosieradzki.androidmviexample.storage.entity.GenderEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GenderDao {
    @Insert
    suspend fun insertAll(genders: List<GenderEntity>)

    @Query("SELECT * FROM genders")
    suspend fun getAll(): List<GenderEntity>
}
