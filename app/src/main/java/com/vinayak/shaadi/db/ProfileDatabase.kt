package com.vinayak.shaadi.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vinayak.shaadi.model.Profile

@Database(entities = [Profile::class,],version = 1)
abstract class ProfileDatabase:RoomDatabase() {
    abstract fun profileDAO() : ProfileDAO
}