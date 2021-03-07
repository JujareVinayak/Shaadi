package com.vinayak.shaadi.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "profile_data_table")
data class Profile(
    val cell: String,
    val dob: Dob,
    val email: String,
    val gender: String,
    @PrimaryKey
    @SerializedName("id")
    @Expose
    val id: Id,
    val location: Location,
    val login: Login,
    val name: Name,
    val nat: String,
    val phone: String,
    val picture: Picture,
    val registered: Registered
)