package com.vinayak.shaadi.api

import com.vinayak.shaadi.model.Profile
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(
    private val apiService: ApiService
): ApiHelper {
    override suspend fun getProfiles(): Response<List<Profile>> = apiService.getProfiles()
    }