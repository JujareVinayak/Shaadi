package com.vinayak.shaadi.api

import com.vinayak.shaadi.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper
){
    suspend fun getProfiles() = apiHelper.getProfiles()
}