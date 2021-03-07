package com.vinayak.shaadi.db

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vinayak.shaadi.model.Profile

interface ProfileDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProfiles(profiles: List<Profile>)

    @Query("SELECT * FROM profile_data_table")
    fun getAllProfiles(): LiveData<List<Profile>>
}