package com.vinayak.shaadi.api

import com.vinayak.shaadi.model.Profile
import retrofit2.Response

interface ApiHelper {
    suspend fun getProfiles(): Response<List<Profile>>
}