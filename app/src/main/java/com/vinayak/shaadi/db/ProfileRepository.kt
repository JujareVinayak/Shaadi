package com.vinayak.shaadi.db

import com.vinayak.shaadi.model.Profile
import javax.inject.Inject

class ProfileRepository @Inject constructor(private val profileDAO: ProfileDAO) {

    val profiles = profileDAO.getAllProfiles()

    suspend fun insertProfiles(profiles: List<Profile>) {
        profileDAO.insertProfiles(profiles)
    }
}