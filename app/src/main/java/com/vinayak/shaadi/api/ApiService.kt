package com.vinayak.shaadi.api

import com.vinayak.shaadi.model.Profile
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService{

    @GET("api/?results=10")
    suspend fun getProfiles(): Response<List<Profile>>

}